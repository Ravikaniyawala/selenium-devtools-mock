package testExecution;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utilities.ConfigurationManager;
import utilities.Session;
import utilities.UIAbstractTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class RetryFailedTestCases extends UIAbstractTest implements IRetryAnalyzer {
    private int retryCnt = 0;
    //You could mentioned maxRetryCnt (Maximiun Retry Count) as per your requirement. Here I took 2, If any failed testcases then it runs two times
    private int maxRetryCnt = Session.getMaxRetryCnt();



    //This method will be called everytime a test fails. It will return TRUE if a test fails and need to be retried, else it returns FALSE
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCnt < maxRetryCnt) {

    // take screenshot for headless re-try failures
            if (Session.isHeadless) {
                LocalDateTime localDateTime = LocalDateTime.now();
                String screenShotName = "screenshot/" + result.getName() + " re-try" + (retryCnt + 1) + "-" + localDateTime;
                File scrFile = ((TakesScreenshot) driverThread.get()).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(scrFile, new File(String.format("%s.png", screenShotName)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Retrying " + result.getName() + " again and the count is " + (retryCnt + 1));
            retryCnt++;
            return true;
        }
        return false;
    }

}
