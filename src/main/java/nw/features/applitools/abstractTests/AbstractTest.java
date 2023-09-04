package nw.features.applitools.abstractTests;

import nw.pageObjects.*;
import org.openqa.selenium.WebDriver;
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

    public boolean setUpHomePage(WebDriver webDriver) throws InterruptedException {
        HomePage homePage = new HomePage(webDriver);
        return homePage.setUpHomePage(webDriver);
    }

    public void gotToPage(WebDriver webDriver, String PAGE) throws InterruptedException {

        switch (PAGE.toLowerCase()){
            case "xyzpage":
                Logger.writeToLog("navigating to "+PAGE);
                break;
            default:
                break;
        }
        return;
    }
    //editOrdertrolleyPriceValidation
    //endregion
}
