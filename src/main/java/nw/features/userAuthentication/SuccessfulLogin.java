package nw.features.userAuthentication;

import nw.features.userAuthentication.helpers.abstractTests.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reporting.Attribute;
import reporting.Document;
import testExecution.RetryFailedTestCases;
import utilities.ConfigurationManager;
import utilities.DataObjects.DataManager;
import utilities.MockService;
import utilities.MyTestResultListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Ravi Kaniyawala on 20/09/18.
 */
@Listeners({MyTestResultListener.class,testExecution.RetryListenerClass.class})
public class SuccessfulLogin extends AbstractTest {

    static final String  DESIGN_LINK = "https://xyz.co.nz";

    //region OBJECTS
    Document document = new Document();
    WebDriver webDriver = null;
    DataManager dataManager = new DataManager();

    @BeforeMethod(alwaysRun = true)
    private void SetUp()throws Exception{
        webDriver = setUpWebDriver("Successful Login with Valid Credentials.");
        dataManager.applitoolsEyes.initEyes(webDriver,getClass().getSimpleName());
        dataManager.mockService = new MockService(webDriver);
        //example of enabling mock service using devtools
        dataManager.mockService.addMock("url","your json");
        //example of enabling network logs using devtools
        dataManager.mockService.enableNetworkLogs(webDriver);
    }

    @Test(retryAnalyzer = RetryFailedTestCases.class,groups = { Attribute.SCENARIO_RISK_HIGH, Attribute.SCENARIO_STATUS_IMPLEMENTED, Attribute.AUTOMATION_IMPLEMENTED})
    public void loginWithValidCredentials() throws Exception {

        document.SOT();
        document.SCENARIO_DETAILS("Successful Login with Valid Credentials.");
        document.EXPOSURE(Attribute.Exposure.INTENDED_USE);
        document.RISK(Attribute.Risk.HIGH);
        document.DESIGN(DESIGN_LINK);
        assertThat(true,is(setUpHomePagee(webDriver)));
        document.GIVEN("I am on NW login page.");
        document.AND("I tap on loginRegister Tab");
        document.THEN("Login button should be disabled");
        document.WHEN("When I enter credentials.");
        document.THEN("Login button should be enabled");
        document.AND("I tap on login button");
        document.THEN("The store landing page must have the user's profile Loaded.");
        document.EOT();
    }
    //endregion

    @AfterMethod(alwaysRun = true)
    private void TearDown() throws Exception{
        dataManager.applitoolsEyes.closeEyes();
        tearDown(webDriver);
    }
    //endregion
}