package utilities;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class MyTestResultListener extends TestListenerAdapter {

    public static String TesResult;

    @Override
    public void onTestFailure(ITestResult result) {
        TesResult = "Fail";
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TesResult = "Pass";
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TesResult = "Skip";
    }
}
