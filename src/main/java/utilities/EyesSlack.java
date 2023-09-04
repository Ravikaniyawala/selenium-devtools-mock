package utilities;

import com.applitools.eyes.TestResultContainer;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.TestResultsSummary;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.List;

public class EyesSlack {
    //https://outlook.office.com/webhook/3d3498a5-181f-416e-a3d4-0689d905f60e@d75f6ca2-45e2-417d-b777-07433f0571e8/IncomingWebhook/ca14d8f30186476fb4f078a4275a06c9/c816e703-72fe-443f-bae2-b1834befd7ca
    final static String slackWebhookURL = "https://hooks.slack.com/services/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";
    //    final static String slackWebhookURL = "https://hooks.slack.com/services/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";

    public static List<TestResults> allTestResults = new ArrayList<>();
    //public static List<EyesRunner> allRunners = new ArrayList<>();
    public static List<TestResultsSummary> alltestResultsSummary = new ArrayList<>();
    public static List<TestResults> forAllTestResults = new ArrayList<>();


    static final String Red = "FF0000";
    static final String Green = "36a64f";
    static final String Orange = "FFA500";
    static final String DarkGrey="A9A9A9";

    static String status = "";
    static int unresolved = 0;
    static String batchUrl = "";
    static String appName = "";
    static String batchName = "";
    static String hostApps = "";
    static int steps = 0;
    static Long startTime;
    static String messageColor = "";
    static String fallback = "";

    public static void createTestResultAndPost(){


        /*for(EyesRunner runner: allRunners){
            alltestResultsSummary.add(runner.getAllTestResultsImpl(false));
        }*/

        for(TestResultsSummary tr:alltestResultsSummary){
            for(TestResultContainer testResults:tr){
                forAllTestResults.add(testResults.getTestResults());
            }
        }

        //System.out.println("\u001B[31m" + "Total number of Test Results" + allTestResults.size() + "\u001B[0m");

        for(TestResults testR:forAllTestResults){
            try {
                System.out.println("Status: " + testR.getStatus().toString());
                status = status + " " + testR.getStatus().toString();

                if (!hostApps.contains(testR.getHostApp())) {
                    hostApps = hostApps + " " + testR.getHostApp();
                }
                System.out.println("\u001B[31m" + "\nTotal Steps: " + testR.getSteps() + "\u001B[0m");
                steps = steps + testR.getSteps();
                unresolved = unresolved + testR.getMismatches() + testR.getMissing() +testR.getNoneMatches();
            }catch (NullPointerException n){
                continue;
            }
        }
        System.out.println("Overall Status: "+status);

        if(status.contains("Failed")){
            status="Failed";
            fallback = "Test Failed";
            messageColor = Red;
        }
        if(status.contains("Unresolved")){
            status="Unresolved";
            fallback = "Test Mismatches Found";
            messageColor = Orange;
        }
        if(status.contains("Passed")){
            status = "Passed";
            fallback = "Test Successfully Completed";
            messageColor = Green;
        }

        System.out.println("Message Color: "+messageColor);
        batchName = Session.getBatchInfo().toString();
        appName = forAllTestResults.get(0).getAppName();
        batchUrl = forAllTestResults.get(0).getAppUrls().getBatch();
        startTime = forAllTestResults.get(0).getStartedAt().getTimeInMillis()/1000;

        //Post message to slack
        post();
    }

    @SuppressWarnings("unused")
    public static void post()
    {
        // Define the color of the slack message
        /*String messageColor = "";

        // Define the default message for the Slack notification
        String fallback = "Applitools Test Results";

        // Check if test was aborted
        if(testResults.isAborted())
            messageColor = "A9A9A9"; // Dark Gray

        // Check the test results and set the color and Slack notification message
        switch(testResults.getStatus())
        {
            case Failed:
                messageColor = "FF0000"; // Red
                fallback = "Test Failed: " + testResults.getName();
                break;
            case Passed:
                messageColor = "36a64f"; // Green
                fallback = "Test Successfully Completed: " + testResults.getName();
                break;
            case Unresolved:
                messageColor = "FFA500"; // Orange
                fallback = "Test Mismatches Found: " + testResults.getName();
                break;
        }*/

        // build the httpClient object which will send our request to Slack Webhook
        HttpClient httpClient = HttpClientBuilder.create().build();

        try {

            // build the HttpPost request object
            HttpPost request = new HttpPost(slackWebhookURL);

            // build the HTTP request
            StringEntity params =new StringEntity("{\n" +
                    "\"channel\": \"#b2cweb-autoresults\",\n" +
                    "\"attachments\": [\n" +
                    "{\n" +
                    "\"fallback\": \"" + fallback + ".\",\n" +
                    "\"color\": \"#" + messageColor + "\",\n" +
                    "\"pretext\": \"" + status + "\",\n" +
                    "\"author_name\": \"Applitools.com\",\n" +
                    "\"author_link\": \"https://foodstuffseyes.applitools.com/\",\n" +
                    "\"author_icon\": \"https://applitools.com/images/favicon.ico\",\n" +
                    "\"title\": \"See Results\",\n" +
                    "\"title_link\": \"" + batchUrl + "\",\n" +
                    "\"fields\": [\n" +
                    "{\n" +
                    "\"title\": \"App\",\n" +
                    "\"value\": \"" + appName + "\",\n" +
                    "\"short\": true\n" +
                    "}\n," +
                    /*"{\n" +
                    "\"title\": \"Test\",\n" +
                    "\"value\": \"" + testResults.getName() + "\",\n" +
                    "\"short\": true\n" +
                    "}\n," +*/
                    "{\n" +
                    "\"title\": \"ENV\",\n" +
                    "\"value\": \"" + ConfigurationManager.env + "\",\n" +
                    "\"short\": true\n" +
                    "}\n," +
                    "{\n" +
                    "\"title\": \"Batch\",\n" +
                    "\"value\": \"" + batchName + "\",\n" +
                    "\"short\": true\n" +
                    "}\n," +
                   /* "{\n" +
                    "\"title\": \"OS\",\n" +
                    "\"value\": \"" + testResults.getHostOS() + "\",\n" +
                    "\"short\": true\n" +
                    "}\n," +*/
                    /*"{\n" +
                    "\"title\": \"Browser\",\n" +
                    "\"value\": \"" + hostApps + "\",\n" +
                    "\"short\": true\n" +
                    "}\n," +*/
                    /*"{\n" +
                    "\"title\": \"Viewport\",\n" +
                    "\"value\": \"" + testResults.getHostDisplaySize() + "\",\n" +
                    "\"short\": true\n" +
                    "}\n," +*/
                    "{\n" +
                    "\"title\": \"unresolved\",\n" +
                    "\"value\": \"" + unresolved + " Steps\",\n" +
                    "\"short\": true\n" +
                    "}\n," +
                    "{\n" +
                    "\"title\": \"Steps\",\n" +
                    "\"value\": \"" + steps + " Steps\",\n" +
                    "\"short\": true\n" +
                    "}\n" +
                    "],\n" +
                    "\"footer\": \"Test Start Time\",\n" +
                    "\"footer_icon\": \"https://applitools.com/images/favicon.ico\",\n" +
                    "\"ts\": " + startTime + "\n" +
                    "}\n" +
                    "]\n" +
                    "}");
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            // Executes the HTTP request
            HttpResponse response = httpClient.execute(request);

        }catch (Exception ex) {
            //handle exception here
        }
    }
}
