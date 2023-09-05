package utilities;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.WebDriver;

import static utilities.EyesSlack.alltestResultsSummary;
import static utilities.MyTestResultListener.TesResult;

public class ApplitoolsEyes {

    private Eyes eyes;
    private EyesRunner runner;

    public ApplitoolsEyes() {
        ConfigurationManager.loadTestConfig();
        eyes = new Eyes();
    }

    public void initEyes(WebDriver webDriver, String testName) {
        if (ConfigurationManager.applitoolsTest.equalsIgnoreCase("true")) {
            initConfiguration();
            eyes = new Eyes(runner);
            eyes.setConfiguration(getTestConfiguration(testName));
            eyes.setMatchTimeout(1000);
            eyes.setLogHandler(new StdoutLogHandler(true));
            eyes.setLogHandler(new FileLogger("../target/generated-sources/Missing_Test_Issue.log", true, true));
            eyes.open(webDriver);
        }
    }

    public void checkEyesWindow(String screenName) {
        if (shouldRunApplitoolsTest()) {
            eyes.setStitchMode(StitchMode.CSS);
            eyes.check(screenName + "_UX", Target.window().fully().matchLevel(MatchLevel.LAYOUT));
        }
    }

    public void checkEyesWindow(String screenName, MatchLevel matchLevel) {
        if (shouldRunApplitoolsTest()) {
            eyes.setStitchMode(StitchMode.CSS);
            eyes.check(screenName + "_UX", Target.window().fully().matchLevel(matchLevel));
        }
    }

    // ... other methods ...

    public void closeEyes() {
        if (shouldRunApplitoolsTest()) {
            if (TesResult.equalsIgnoreCase("Fail")) {
                eyes.abortAsync();
            } else {
                eyes.closeAsync();
                TestResultsSummary testResultsSummary = runner.getAllTestResults(false);
                System.out.println(testResultsSummary.toString());
                alltestResultsSummary.add(testResultsSummary);
            }
        }
    }

    // ... other methods ...

    private void initConfiguration() {
        if (ConfigurationManager.useVisualGrid.equalsIgnoreCase("true")) {
            runner = new VisualGridRunner(20);
            runner.setDontCloseBatches(true);
        } else {
            runner = new ClassicRunner();
        }
    }

    private Configuration getTestConfiguration(String testName) {
        Configuration config = new Configuration();
        config.setTestName(testName + "_UX");
        config.setApiKey("your api key");
        config.setServerUrl("your server url");
        config.setAppName(ConfigurationManager.brand.toUpperCase() + "WEB");
        config.setBatch(Session.getBatchInfo());
        // Set other config properties...
        /*suiteConfig
                            .addBrowser(1920, 1080, BrowserType.CHROME, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Chrome_1920x1080")
                            .addBrowser(1920, 1080, BrowserType.FIREFOX, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Firefox_1920x1080")
                            .addBrowser(1920, 1080, BrowserType.EDGE_CHROMIUM, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Edge_1920x1080")
                            .addBrowser(1366, 768, BrowserType.CHROME, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Chrome_1366x768")
                            .addBrowser(1366, 768, BrowserType.FIREFOX, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Firefox_1366x768")
                            .addBrowser(1366, 768, BrowserType.EDGE_CHROMIUM, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Edge_1366x768")
                            .addBrowser(1366, 768, BrowserType.SAFARI, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Safari_1366x768")
                            .addBrowser(new DesktopBrowserInfo(new RectangleSize(1366, 768),BrowserType.SAFARI,ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Safari_1366x768"))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_13))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_13_Pro_Max))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_13_Pro))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_XR))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_Pro_3))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_9))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_Air_4))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro_Max))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12))
                            .setLayoutBreakpoints(true)
                            .setApiKey("your api key")
                            .setServerUrl("your server url")
                            .setAppName(ConfigurationManager.brand.toUpperCase() + "WEB")
                            .setIgnoreDisplacements(true)
                            .setBranchName(ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_ECOMM")
                            .setMatchTimeout(1)
                            .setViewportSize(new RectangleSize(2048,1536));
                    suiteConfig.setBatch(Session.getBatchInfo());*/
        return config;
    }

    private boolean shouldRunApplitoolsTest() {
        return ConfigurationManager.applitoolsTest.equalsIgnoreCase("true");
    }
}
