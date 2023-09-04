package utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v108.browser.Browser;
import org.openqa.selenium.devtools.v108.browser.model.PermissionType;
import org.openqa.selenium.devtools.v108.cachestorage.model.Header;
import org.openqa.selenium.devtools.v108.emulation.Emulation;
import org.openqa.selenium.devtools.v108.fetch.Fetch;

import org.openqa.selenium.devtools.v108.network.Network;
import org.openqa.selenium.devtools.v108.network.model.Request;

import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.openqa.selenium.remote.http.Contents.utf8String;
import static org.testng.AssertJUnit.*;

public class MockService{

    private final ThreadLocal<DevTools> devTools = new ThreadLocal<>();
    private final ThreadLocal<NetworkInterceptor> interceptor = new ThreadLocal<>();
    private final ConcurrentHashMap<String, String> mockResponses = new ConcurrentHashMap<>();

    final List<Map<String, String>> requestDetails = Collections.synchronizedList(new ArrayList<>());

    public MockService(WebDriver driver) {
        devTools.set(((HasDevTools) driver).getDevTools());
        devTools.get().createSession();
        devTools.get().send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        interceptor.set(new NetworkInterceptor(driver, createRoute()));
    }

    private Route createRoute() {
        return Route.matching(req -> containsKey(req.getUri())).to(() -> req -> {
            String responseContent = getResponse(req.getUri());
            if (responseContent != null) {
                return new HttpResponse()
                        .setStatus(200)
                        .addHeader("Content-Type", "application/json")
                        .setContent(utf8String(responseContent));
            } else {
                return null;
            }
        });
    }

    private boolean containsKey(String uri) {
        return mockResponses.keySet().stream().anyMatch(uri::contains);
    }

    private String getResponse(String uri) {
        return mockResponses.entrySet().stream()
                .filter(entry -> uri.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public synchronized void addMock(String uriPart, String responseContent) {
        mockResponses.put(uriPart, responseContent);
    }

    public synchronized void clearMocks() {
        mockResponses.clear();
    }

    public void remove() {
        // Closes and removes the DevTools sessions associated with the current thread
        Optional.ofNullable(devTools.get()).ifPresent(dt -> {
            dt.close();
            devTools.remove();
        });

        // Removes the NetworkInterceptor associated with the current thread
        Optional.ofNullable(interceptor.get()).ifPresent(in -> {
            in.close();
            interceptor.remove();
        });

        // Clears all mock responses associated with the current thread
        clearMocks();
    }

    public void enableNetworkLogs(WebDriver webDriver) {
        requestDetails.clear();
        DevTools dt = devTools.get();
        AtomicBoolean redirected = new AtomicBoolean(false);  // Step 1

        dt.addListener(Network.requestWillBeSent(),
                request -> {
                    synchronized (requestDetails) {
                        Request actualRequest = request.getRequest();
                        String actualUrl = actualRequest.getUrl();

                        String actualMethod = actualRequest.getMethod();
                        String requestId = request.getRequestId().toJson();
                        String body = request.getRequest().getPostData().toString();
                        String headers = request.getRequest().getHeaders().toString();

                        Map<String, String> details = new HashMap<>();
                        details.put("URL", actualUrl);
                        details.put("Method", actualMethod);
                        details.put("requestId", requestId);
                        details.put("Body", body);
                        details.put("headers", headers);
                        requestDetails.add(details);
                    }
                });

        dt.addListener(Network.responseReceived(),
                responseReceived -> {

                    synchronized (requestDetails) {
                        Iterator<Map<String, String>> iterator = requestDetails.iterator();
                        while (iterator.hasNext()) {
                            Map<String, String> details = iterator.next();
                            try {
                                if (details.get("requestId").contains(responseReceived.getRequestId().toJson())) {
                                    details.put("StatusCode", responseReceived.getResponse().getStatus().toString());
                                }
                            } catch (NullPointerException n) {
                                // handle exception
                            }
                        }
                    }

                });
        dt.addListener(Fetch.requestPaused(), requestPaused -> Fetch.continueRequest(requestPaused.getRequestId(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.of(false)));
    }


    public void reset_GeoLocation() {
        devTools.get().send(Emulation.clearGeolocationOverride());
    }

    public void set_GeoLocation(WebDriver webDriver, String lat, String lon){
        // Cast WebDriver to HasDevTools
        /*HasDevTools hasDevTools = (HasDevTools) webDriver;

        DevTools devTools = hasDevTools.getDevTools();*/
        //devTools.createSession();

        Map<String, Object> coordinates = Map.of(
                "latitude", Float.parseFloat(lat),
                "longitude", Float.parseFloat(lon),
                "accuracy", 1
        );

        // Grant geolocation permission
        devTools.get().send(Browser.grantPermissions(
                List.of(PermissionType.GEOLOCATION),
                Optional.empty(),
                Optional.empty()
        ));

        // Set geolocation
        devTools.get().send(Emulation.setGeolocationOverride(
                Optional.of(Double.valueOf(coordinates.get("latitude").toString())),
                Optional.of(Double.valueOf(coordinates.get("longitude").toString())),
                Optional.of(Integer.valueOf(coordinates.get("accuracy").toString()))
        ));
    }
    public List<Header> getHeaders(Map<String, Object> headersMap) {
        List<Header> headers = new ArrayList<>();

        for (Map.Entry<String, Object> entry : headersMap.entrySet()) {
            headers.add(new Header(entry.getKey(), entry.getValue().toString()));
        }

        return headers;
    }

    public void logAllGtmRequests(){
        synchronized (requestDetails) {
            Iterator<Map<String, String>> iterator = requestDetails.iterator();
            while (iterator.hasNext()) {
                Map<String, String> details = iterator.next();
                if(details.get("URL").contains("gtmsspns")){

                    String urlString = details.get("URL"); // your URL here
                    String[] urlParts = urlString.split("\\?"); // split the base URL and parameters
                    if (urlParts.length > 1) {
                        String query = urlParts[1];
                        String[] params = query.split("&"); // split the parameters
                        Map<String, String> map = new HashMap<>();
                        for (String param : params) {
                            String[] p = param.split("=");
                            String key = p[0];
                            String value = "";
                            if (p.length > 1) { // add a check here
                                value = URLDecoder.decode(p[1], StandardCharsets.UTF_8); // decode the value
                            }
                            map.put(key, value);
                        }
                        // print the map
                        for (String key : map.keySet()) {
                            Logger.writeToLog(key + ": " + map.get(key));
                        }
                    }
                }


            }

        }
    }

    public void verifyGtmRequests(String eventOf, String eventType, int expectedCount) {
        List<Map<String, String>> validRequests = requestDetails.stream()
                .filter(details -> details.get("URL").contains("gtmsspns"))
                .map(details -> parseUrl(details.get("URL")))
                .filter(params -> eventOf.equals(params.get("pal")) && eventType.equals(params.get("ea")))
                .collect(Collectors.toList());

        validRequests.forEach(request -> Logger.writeToLog("URL: " + request.get("URL") + " has verified parameter values."));

        int actualCount = validRequests.size();

        assert actualCount == expectedCount : String.format("Expected %d URLs, but found %d", expectedCount, actualCount);
    }

    private Map<String, String> parseUrl(String urlString) {
        Map<String, String> paramMap = new HashMap<>();
        try {
            URL url = new URL(urlString);
            String query = url.getQuery();

            for (String param : query.split("&")) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : "";
                paramMap.put(key, value);
            }
        } catch (Exception e) {
            // Log or handle the exception
        }
        return paramMap;
    }


    public void verifyGtmRequests(String eventType, int expectedCount) {
        List<Map<String, String>> validRequests = requestDetails.stream()
                .filter(details -> details.get("URL").contains("gtmsspns"))
                .map(details -> parseUrl(details.get("URL")))
                .filter(params -> eventType.equals(params.get("ea")))
                .collect(Collectors.toList());

        validRequests.forEach(request -> Logger.writeToLog("URL: " + request.get("URL") + " has verified parameter values."));

        int actualCount = validRequests.size();

        assert actualCount == expectedCount : String.format("Expected %d URLs, but found %d", expectedCount, actualCount);
    }


    public void logAllRequests(){
        synchronized (requestDetails) {
            Iterator<Map<String, String>> iterator = requestDetails.iterator();
            while (iterator.hasNext()) {
                Map<String, String> details = iterator.next();
                Logger.writeToLog("URL: " + details.get("URL") + "\n" +
                        "Method: " + details.get("Method") + "\n" +
                        "StatusCode: " + details.get("StatusCode") + "\n" +
                        "Body: " + details.get("Body") + "\n" +
                        "Headers: " + details.get("headers") + "\n");
            }
        }


    }


    public boolean isRequestPresent(String url, String method,String statusCode,String eventType, String adID,String clickUrl) {
        for (Map<String, String> details : requestDetails) {
            if (details.get("URL").contains(url) && method.equals(details.get("Method")) && statusCode.equals(details.get("StatusCode"))) {
                System.out.println(details.get("Body"));
                JSONParser js = new JSONParser(details.get("Body").replace("Optional",""));
                assertEquals(js.findValueAnyLevel("adID"),adID);
                assertTrue(js.findValueAnyLevel("banner").equalsIgnoreCase(Session.isNw?"NW":"PNS"));
                assertTrue(js.findValueAnyLevel("type").equalsIgnoreCase(eventType));
                assertFalse(js.findValueAnyLevel("url").isEmpty());
                return true;
            }
        }
        return false;
    }


    public boolean isRequestPresent(String url, String method,String statusCode,String pId,int qty) {
        for (Map<String, String> details : requestDetails) {
            if (details.get("URL").contains(url) && method.equals(details.get("Method")) && statusCode.equals(details.get("StatusCode"))) {
                JSONParser js = new JSONParser(details.get("Body").replace("Optional",""));
                if(js.findValueAnyLevel("productId").contains(pId)) {
                    assertEquals(js.findValueAnyLevel("productId"), pId);
                    assertEquals(Integer.parseInt(js.findValueAnyLevel("qty")), qty);
                    assertFalse(js.findValueAnyLevel("conversionUrl").isEmpty());
                    return true;
                }

            }
        }
        return false;
    }

    public boolean isRequestPresent(String url, String method,String statusCode) {
        for (Map<String, String> details : requestDetails) {
            if (details.get("URL").contains(url) && method.equals(details.get("Method")) && statusCode.equals(details.get("StatusCode"))) {
                System.out.println(details.get("Body"));
                return true;
            }
        }
        return false;
    }
}