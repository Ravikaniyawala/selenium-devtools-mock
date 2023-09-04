package nw.features.userAuthentication.helpers.abstractTests;

import nw.pageObjects.*;
import org.openqa.selenium.WebDriver;
import utilities.DataObjects.DataManager;
import utilities.Logger;
import utilities.UIAbstractTest;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class AbstractTest extends UIAbstractTest {

    @Override
    public WebDriver setUpWebDriver(String Scenario) throws Exception {
        return super.setUpWebDriver(Scenario);
    }

    @Override
    public void tearDown(WebDriver webDriver) throws Exception {
        super.tearDown(webDriver);
    }

    public boolean setUpHomePagee(WebDriver webDriver) throws InterruptedException {
        HomePage homePage = new HomePage(webDriver);
        return homePage.setUpHomePage(webDriver);
    }

    public void gotToPage(String Page, WebDriver webDriver, DataManager dataManager) throws Exception {

        switch (Page.toLowerCase()){
            case "methodselectionpage":
                Logger.writeToLog("Navigating to "+Page);
                break;
            default:
        }
    }
}
