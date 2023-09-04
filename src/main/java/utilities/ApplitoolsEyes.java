package utilities;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.*;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.EyesSlack.alltestResultsSummary;
import static utilities.MyTestResultListener.TesResult;


public class ApplitoolsEyes {

    Eyes eyes;
    EyesRunner runner;
    /*public static final ThreadLocal<Eyes> eyesThread = new ThreadLocal<Eyes>();
    public static final ThreadLocal<EyesRunner> eyesRunnerThread = new ThreadLocal<EyesRunner>();*/
    //static BatchInfo batch;

    public ApplitoolsEyes() {
        ConfigurationManager.loadTestConfig();
        this.eyes = new Eyes();
    }

    public void initEyes(WebDriver webDriver, String testName) {
        System.out.println("init eyes");


        if (ConfigurationManager.applitoolsTest.equalsIgnoreCase("true")) {
            Configuration suiteConfig;

            if (ConfigurationManager.useVisualGrid.equalsIgnoreCase("true")) {
                this.runner = new VisualGridRunner(20);
                this.runner.setDontCloseBatches(true);
                suiteConfig = new Configuration();

                if(Session.isMobile) {

                    suiteConfig.addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_11_Pro))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_11_Pro_Max))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_11))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_XR))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_Pro_3))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_7))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_Air_2))
                            .addMobileDevices(new AndroidDeviceInfo(AndroidDeviceName.Galaxy_S22_Plus))
                            .addMobileDevices(new AndroidDeviceInfo(AndroidDeviceName.Pixel_6))
                            .addMobileDevices(new AndroidDeviceInfo(AndroidDeviceName.Galaxy_S22))
                            .addMobileDevices(new AndroidDeviceInfo(AndroidDeviceName.Galaxy_S21_ULTRA))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro_Max))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12))
                            .setLayoutBreakpoints(true)

                            .setApiKey("W8syZk8NsA5ZesE97pTOGsPdVftJzhN7PPapZxeISBC0110")
                            .setServerUrl("https://foodstuffseyesapi.applitools.com")
                            .setAppName(ConfigurationManager.brand.toUpperCase() + "WEB")
                            .setViewportSize( new RectangleSize(393,851))
                            .setIgnoreDisplacements(true)
                            .setBranchName(ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_ECOMM")
                            //.setBatch(Session.getBatchInfo())
                            .setMatchTimeout(1);
                    suiteConfig.setBatch(Session.getBatchInfo());
                }

                if(Session.isHeadless || Session.isChrome) {
                    suiteConfig//1920x1080
                            //.addBrowser(1024,786,BrowserType.CHROME,ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase()+"_Desktop")
                            .addBrowser(1920, 1080, BrowserType.CHROME, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Chrome_1920x1080")
                            //.addBrowser(1024, 786, BrowserType.FIREFOX,ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase()+"_Desktop")
                            .addBrowser(1920, 1080, BrowserType.FIREFOX, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Firefox_1920x1080")
                            /*.addBrowser(1920, 1080, BrowserType.IE_11, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_IE11_1920x1080")*/
                            //.addBrowser(1024, 786, BrowserType.IE_11,ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase()+"_Desktop")
                            .addBrowser(1920, 1080, BrowserType.EDGE_CHROMIUM, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Edge_1920x1080")
                            //.addBrowser(1920, 1080, BrowserType.SAFARI, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Safari_1920x1080")

                            .addBrowser(1366, 768, BrowserType.CHROME, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Chrome_1366x768")
                            //.addBrowser(900, 600, BrowserType.CHROME,ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase()+"_Desktop")
                            .addBrowser(1366, 768, BrowserType.FIREFOX, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Firefox_1366x768")
                            //.addBrowser(900, 600, BrowserType.FIREFOX,ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase()+"_Desktop")
                            //.addBrowser(900, 600, BrowserType.IE_10,ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase()+"_Desktop")
                            .addBrowser(1366, 768, BrowserType.EDGE_CHROMIUM, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Edge_1366x768")
                            /*.addBrowser(1366, 768, BrowserType.IE_11, Configu rationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_IE11_1366x768")*/
                            .addBrowser(1366, 768, BrowserType.SAFARI, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Safari_1366x768")
                            .addBrowser(new DesktopBrowserInfo(new RectangleSize(1366, 768),BrowserType.SAFARI,ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_Safari_1366x768"))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_13))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_13_Pro_Max))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_13_Pro))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_XR))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_Pro_3))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_9))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPad_Air_4))
//                            .addMobileDevices(new AndroidDeviceInfo(AndroidDeviceName.Galaxy_S22_Plus))
//                            .addMobileDevices(new AndroidDeviceInfo(AndroidDeviceName.Pixel_6))
//                            .addMobileDevices(new AndroidDeviceInfo(AndroidDeviceName.Galaxy_S22))
//                            .addMobileDevices(new AndroidDeviceInfo(AndroidDeviceName.Galaxy_S21_ULTRA))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro_Max))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro))
                            .addMobileDevices(new IosDeviceInfo(IosDeviceName.iPhone_12))
                            .setLayoutBreakpoints(true)
                            //.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT, ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "MobileP_iPhone_X")
                            // set up default Eyes configuration values
                            .setApiKey("W8syZk8NsA5ZesE97pTOGsPdVftJzhN7PPapZxeISBC0110")
                            .setServerUrl("https://foodstuffseyesapi.applitools.com")
                            .setAppName(ConfigurationManager.brand.toUpperCase() + "WEB")
                            //.setViewportSize( new RectangleSize(2048, 1536))
                            .setIgnoreDisplacements(true)
                            .setBranchName(ConfigurationManager.brand.toUpperCase() + "_" + ConfigurationManager.env.toUpperCase() + "_ECOMM")
                            //.setBatch(Session.getBatchInfo())
                            .setMatchTimeout(1)
                            .setViewportSize(new RectangleSize(2048,1536));
                    suiteConfig.setBatch(Session.getBatchInfo());
                }

            } else {
                this.runner = new ClassicRunner();
                suiteConfig = new Configuration();
                suiteConfig
                        .setApiKey("W8syZk8NsA5ZesE97pTOGsPdVftJzhN7PPapZxeISBC0110")
                        .setServerUrl("https://foodstuffseyesapi.applitools.com")
                        .setAppName(ConfigurationManager.brand.toUpperCase()+"WEB")
                        .setViewportSize( new RectangleSize(2048, 1536))
                        .setBranchName(ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase()+"_ECOMM")
                        .setBatch(Session.getBatchInfo());
            }
            eyes = new Eyes(this.runner);
            eyes.setConfiguration(suiteConfig);
            eyes.setMatchTimeout(1000);
            Configuration testConfig = eyes.getConfiguration();
            testConfig.setTestName(testName+"_UX");
            eyes.setConfiguration(testConfig);
            //applitools debug log, enable when required
            eyes.setLogHandler(new StdoutLogHandler(true));
            eyes.setLogHandler(new FileLogger("../target/generated-sources/Missing_Test_Issue.log",true,true));
            System.out.println("xxxxxxxxxxxxxxBatch id: "+eyes.getConfiguration().getBatch().getId()+"xxxxxxxxxxxxxxxxxxxxxxxxxxx");
            eyes.open(webDriver);
        }
    }

    public void checkEyesWindow(WebDriver webDriver, String screenName) {
        if (ConfigurationManager.applitoolsTest.equalsIgnoreCase("true")) {
            eyes.setStitchMode(StitchMode.CSS);
            eyes.check(screenName+"_UX",
                    Target.window()
                            .fully()
                            .matchLevel(MatchLevel.LAYOUT)
            );
        }
    }

    public void checkEyesWindow(WebDriver webDriver, String screenName, MatchLevel matchLevel) {
        if (ConfigurationManager.applitoolsTest.equalsIgnoreCase("true")) {
            eyes.setStitchMode(StitchMode.CSS);
            eyes.check(screenName+"_UX",
                    Target.window()
                            .fully()
                            .matchLevel(matchLevel));
        }
    }


    public void checkEyesWindowForSpecificRegion(WebDriver webDriver, String screenName, MatchLevel matchLevel, By by) {
        if (ConfigurationManager.applitoolsTest.equalsIgnoreCase("true")) {
            eyes.setForceFullPageScreenshot(false);
            eyes.check(screenName+"_UX", Target.region(by).matchLevel(matchLevel));
        }
    }


    public void checkEyesByIgnoreSpecificRegion(WebDriver webDriver, String screenName, By[] ignoreByList, MatchLevel matchLevel) {
        if (ConfigurationManager.applitoolsTest.equalsIgnoreCase("true")) {
            eyes.check(screenName+"_UX",
                    Target.window().fully()
                            .ignore(ignoreByList)
                            .matchLevel(matchLevel)
            );
        }
    }


    public void closeEyes () {

        if (ConfigurationManager.applitoolsTest.equalsIgnoreCase("true")) {
            TestResults testResults;

            //allRunners.add(runner);
            if (TesResult.equalsIgnoreCase("Fail")) {
                eyes.abortAsync();
            } else {
                eyes.closeAsync();
                TestResultsSummary testResultsSummary = this.runner.getAllTestResults(false);
                System.out.println(testResultsSummary.toString());
                alltestResultsSummary.add(testResultsSummary);
            }
        }

    }

    /*public void sendSlackNotification () {
        if (ConfigurationManager.applitoolsTest.equalsIgnoreCase("true")) {
            TestResults testResults;
            if (TesResult.equalsIgnoreCase("Fail")) {
                testResults = eyes.abortIfNotClosed();
            } else {
                testResults = eyes.close(false);
            }

        }
    }*/

}