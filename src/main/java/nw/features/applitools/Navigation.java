package nw.features.applitools;

import nw.features.api.FoodstuffsApi;
import nw.features.applitools.abstractTests.AbstractTest;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reporting.Attribute;
import reporting.Document;
import testExecution.RetryFailedTestCases;
import utilities.DataObjects.DataManager;
import utilities.DataObjects.UserModel;
import utilities.MyTestResultListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@Listeners({MyTestResultListener.class,testExecution.RetryListenerClass.class})
public class Navigation extends AbstractTest{
    static final String  DESIGN_LINK = "https://foodstuffsni.atlassian.net/wiki/spaces/B2CWEB/pages/187695270/Navigation+Access#Navigation&Access-SteppedNavigation:Category0";

    //region OBJECTS
    Document document = new Document();
    WebDriver webDriver = null;
    int count=0;
    DataManager dataManager = new DataManager();
    FoodstuffsApi api = new FoodstuffsApi();
    UserModel userModel = new UserModel();
    //endregion

    //region SET UP - BEFORE CLASS (Used by all test methods in the class)
    @BeforeMethod(alwaysRun = true)
    private void SetUp()throws Exception{
        webDriver = setUpWebDriver("Stepped Navigation : Verify Category Navigation");
        dataManager.applitoolsEyes.initEyes(webDriver,getClass().getSimpleName());
    }

    @Test(retryAnalyzer = RetryFailedTestCases.class,groups = {"smoketest","regressiontest","genericpage","mobile",Attribute.SCENARIO_RISK_HIGH, Attribute.SCENARIO_STATUS_IMPLEMENTED, Attribute.AUTOMATION_IMPLEMENTED})
    public void catNavigation() throws Exception {
        document.SOT();
        document.SCENARIO_DETAILS("Stepped Navigation : Verify Category Navigation");
        document.EXPOSURE(Attribute.Exposure.INTENDED_USE);
        document.RISK(Attribute.Risk.HIGH);
        document.DESIGN(DESIGN_LINK);
        assertThat(true, Matchers.is(setUpHomePage(webDriver)));
        dataManager.applitoolsEyes.checkEyesWindow(webDriver,"HOMEPAGE");
        document.THEN("I should see subcategory page loaded");
        document.EOT();
    }


    @AfterMethod(alwaysRun = true)
    private void TearDown() throws Exception{
        dataManager.applitoolsEyes.closeEyes();
        tearDown(webDriver);
    }
    //endregion
}
