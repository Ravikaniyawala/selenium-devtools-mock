package utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import reporting.CustomTableBuilder;
import reporting.Document;
import resource.configuration.Configuration;
import testExecution.ConfigHelper;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utilities.MyTestResultListener.TesResult;

public class UIAbstractTest implements BasePage {

    private static final String CHROME_CAPABILITY_WINDOW_MAXIMIZED = "start-maximized";
    protected Configuration configuration;


    public String currentSce;
    static final LocalDateTime lDateTime = LocalDateTime.now();



    //region OUTPUT FORMATTER AND HANDLERS OBJECTS
    protected WebDriver webDriver ;
    public static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();


    //region WEB DRIVER INITIALIZATION
    public WebDriver setUpWebDriver(String Scenario) throws Exception {


        this.currentSce = Scenario;
        ConfigurationManager.loadTestConfig();
        Map<String, Object> prefs = new HashMap<String, Object>();
        Map<String, Object> profile = new HashMap<String, Object>();
        Map<String, Object> contentSettings = new HashMap<String, Object>();

        if (ConfigHelper.TEST_EXECUTION_CONFIGURATION == null) {
            ConfigHelper.setResources();
        }

        if(Session.isChrome) {
                    System.out.println("INITIATING CHROME for " + ConfigurationManager.env.toUpperCase());
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--window-size=2048,1536");
                    chromeOptions.addArguments(CHROME_CAPABILITY_WINDOW_MAXIMIZED);
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--incognito");
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    contentSettings.put("geolocation", 1);
                    profile.put("managed_default_content_settings", contentSettings);
                    prefs.put("profile", profile);
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    WebDriverManager.chromedriver().capabilities(chromeOptions).setup();
                    webDriver = new ChromeDriver(chromeOptions);
                    webDriver.manage().window().maximize();
                    driverThread.set(webDriver);
            }
            else if(Session.isHeadless) {
                    System.out.println("INITIATING Chrome headless for " + ConfigurationManager.env.toUpperCase());
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--window-size=2048,1536");
                    chromeOptions.addArguments(CHROME_CAPABILITY_WINDOW_MAXIMIZED);
                    chromeOptions.addArguments("--enable-javascript");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--incognito");
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.addArguments("--use-angle=default");
                    chromeOptions.addArguments("--remote-allow-origins=*");

                    contentSettings.put("geolocation", 1);
                    profile.put("managed_default_content_settings", contentSettings);
                    prefs.put("profile", profile);
                    chromeOptions.setExperimentalOption("prefs", prefs);

                    try{
                        WebDriverManager.chromedriver().forceDownload().capabilities(chromeOptions).setup();
                        webDriver = new ChromeDriver(chromeOptions);
                    }catch (IllegalStateException | NullPointerException | SessionNotCreatedException i ) {
                        WebDriverManager.chromedriver().driverVersion("104").forceDownload().capabilities(chromeOptions).setup();
                        webDriver = new ChromeDriver(chromeOptions);
                    }


                    webDriver.manage().window().maximize();
                    driverThread.set(webDriver);
                }else if(Session.IsEdgeHeadless) {

                    System.out.println("INITIATING Chrome headless for " + ConfigurationManager.env.toUpperCase());
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--window-size=2048,1536");
                    edgeOptions.addArguments(CHROME_CAPABILITY_WINDOW_MAXIMIZED);
                    edgeOptions.addArguments("--disable-extensions");
                    edgeOptions.addArguments("--incognito");
                    edgeOptions.addArguments("--ignore-certificate-errors");
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    contentSettings.put("geolocation", 1);
                    profile.put("managed_default_content_settings", contentSettings);
                    prefs.put("profile", profile);
                    edgeOptions.setExperimentalOption("prefs", prefs);
                    WebDriverManager.edgedriver().capabilities(edgeOptions).setup();
                    webDriver = new EdgeDriver(edgeOptions);

                    webDriver.manage().window().maximize();
                    driverThread.set(webDriver);
        }
            else if(Session.isMobile){
                    Map<String, Object> deviceMetrics = new HashMap<>();
                    Map<String, Object> mobileEmulation = new HashMap<>();
                    contentSettings.put("geolocation", 1);
                    profile.put("managed_default_content_settings", contentSettings);
                    prefs.put("profile", profile);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--window-size=393,851");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    chromeOptions.addArguments("--headless");
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(chromeOptions);
                    driverThread.set(webDriver);
        }

        return webDriver;
    }

    public void tearDown(WebDriver webDriver) throws Exception {
        if (TesResult.equalsIgnoreCase("Fail")) {
            List<LogEntry> entries = webDriver.manage().logs().get(LogType.BROWSER).getAll();
            System.out.println(entries.size() + " " + LogType.BROWSER + " log entries found");
            for (LogEntry entry : entries) {
                Logger.writeToLog(entry.getMessage());
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            String screenShotName = "screenshot/" + currentSce.trim() + "-" + localDateTime;
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(String.format("%s.png", screenShotName)));
        }
        webDriver.quit();
    }


    //endregion
}
