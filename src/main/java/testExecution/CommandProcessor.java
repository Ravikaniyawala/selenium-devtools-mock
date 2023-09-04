package testExecution;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import reporting.CustomTableBuilder;
import reporting.Document;
import resource.configuration.Configuration;
import utilities.DateAndTime;

import java.util.*;

import static testExecution.ConfigHelper.TEST_EXECUTION_CONFIGURATION;

public class CommandProcessor {

    //region DOCUMENTATION
    private static CustomTableBuilder builder = new CustomTableBuilder();
    private static Document document = new Document();
    //endregion

    //region MESSAGES FOR OUTPUT
    private final static String MESSAGE_NO_MATCHING_COMMAND_ENTERED = "\nNO PROGRAM ARGUMENT WAS ENTERED OR WAS NOT RECOGNIZED FROM THE LIST OF EXISTING COMMANDS, REVIEW THE COMMAND LIST AGAIN.\n\n";
    private final static String MESSAGE_NO_MATCHING_TESTS_FOUND = "\nNONE OF THE PROGRAM ARGUMENTS MATCHED ANY CATEGORY, FEATURE OR SCENARIO CLASSES FROM THE LIST BELOW.\n" + "SUPPLY CATEGORIES or FEATURES or SCENARIO CLASSES  AS ARGUMENTS SEPARATED BY A COMMA TO THE JAR ACCORDINGLY.\n\n";
    private final static String MESSAGE_COMMAND_PREFIX_DOES_NOT_MATCH = "\nCOMMAND PREFIX CANNOT BE MATCHED.\n" + "PLEASE PREFIX YOUR PROGRAM ARGUMENTS WITH ANY OF THE FOLLOWING PREFIXES:";
    private final static String MESSAGE_COMMAND_TYPE_SPECIFIED = "IS THE COMMAND TYPE SPECIFIED\n";
    private final static String MESSAGE_UN_IMPLEMENTED_PREFIX = "THE COMMAND PREFIX SPECIFIED IS NOT Implemented. ADD HANDLING OF PREFIX TO CommandProcessor.java\n";
    private final static String MESSAGE_PREFIX_SCENARIO_CLASS = "    SCENARIO CLASS:";
    private final static String MESSAGE_PREFIX_FEATURE = "  FEATURE:";
    private final static String MESSAGE_PREFIX_CATEGORY = "CATEGORY:";

    private final static String CHECKING_SCENARIO_CLASS_AVAILABILITY = "CHECKING SCENARIO CLASS AVAILABILITY";
    private final static String SCENARIO_CLASS_FOUND = "SCENARIO CLASS FOUND";
    private final static String SCENARIO_CLASS_NOT_FOUND = "SCENARIO CLASS NOT FOUND";
    private final static String CHECKING_FEATURE_AVAILABILITY = "CHECKING FEATURE AVAILABILITY";
    private final static String FEATURE_FOUND = "FEATURE FOUND";
    private final static String FEATURE_NOT_FOUND = "FEATURE NOT FOUND";
    private final static String CHECKING_CATEGORY_AVAILABILITY = "CHECKING CATEGORY AVAILABILITY";
    private final static String CATEGORY_FOUND = "CATEGORY FOUND";
    private final static String CATEGORY_NOT_FOUND = "CATEGORY NOT FOUND";
    //endregion

    //region FORMAL PARAMETERS
    private final static String COMMAND_SEPARATOR = ",";
    private final static String SCENARIO_CLASSES = "SCENARIOS:";
    private final static String FEATURES = "FEATURES:";
    private final static String CATEGORIES = "CATEGORIES:";
    private final static String FAILED_FEATURES = "FAILED FEATURES";
    private final static String UTILITIES = "UTILITIES:";
    private final static String DEMO = "DEMO";
    private final static String STATUS_REPORT = "STATUS REPORT";
    private final static String REPORTNG_HTML_REPORTER = "org.uncommons.reportng.HTMLReporter";
    //endregion

    //region PROCESS THE CSV STRING COMMAND AND RUN FEATURES
    public void processCommand(String command) throws Exception {

        //Set the Resources
         ConfigHelper.setResources();

        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        if (command != null) {

            if (isCommandValid(TEST_EXECUTION_CONFIGURATION, command) != null) {

                switch (isCommandValid(TEST_EXECUTION_CONFIGURATION, command)) {

                    case SCENARIO_CLASSES:
                        document.display(SCENARIO_CLASSES + MESSAGE_COMMAND_TYPE_SPECIFIED);
                        suites = suitesFromTestClasses(command.replace(SCENARIO_CLASSES, ""));
                        break;

                    case FEATURES:
                        document.display(FEATURES + MESSAGE_COMMAND_TYPE_SPECIFIED);
                        suites = suitesFromTests(command.replace(FEATURES, ""));
                        break;

                    case CATEGORIES:
                        document.display(CATEGORIES + MESSAGE_COMMAND_TYPE_SPECIFIED);
                        suites = suitesFromSuites(command.replace(CATEGORIES, ""));
                        break;

                    case FAILED_FEATURES:
                        document.display(FAILED_FEATURES + MESSAGE_COMMAND_TYPE_SPECIFIED);
                        break;

                    case UTILITIES:
                        document.display(UTILITIES + MESSAGE_COMMAND_TYPE_SPECIFIED);
                        break;

                    case DEMO:
                        document.display(DEMO + MESSAGE_COMMAND_TYPE_SPECIFIED);
                        break;

                    case STATUS_REPORT:
                        document.display(STATUS_REPORT + MESSAGE_COMMAND_TYPE_SPECIFIED);
                        break;

                    default:
                        document.display(MESSAGE_UN_IMPLEMENTED_PREFIX);
                        break;
                }

                if (suites != null) {
                    runTest(suites);
                }
            } else {
                document.output(MESSAGE_COMMAND_PREFIX_DOES_NOT_MATCH);
                for (String prefix : TEST_EXECUTION_CONFIGURATION.getCommandPrefixes().getPrefix()) {
                    document.output(prefix);
                }
            }
        } else {
            ConsoleInputOutput.showOutput(MESSAGE_NO_MATCHING_COMMAND_ENTERED);
        }
    }
    //endregion

    //region CSV COMMAND VALIDATION, OUTPUT AND NOTIFICATION - NO MATCHING FEATURES
    private String isCommandValid(Configuration configuration, String command) {

        for (String prefix : configuration.getCommandPrefixes().getPrefix()) {
            if (command.toUpperCase().startsWith(prefix)) {
                return prefix;
            }
        }
        return null;
    }

    private void noMatchingTests() {
        document.output(MESSAGE_NO_MATCHING_TESTS_FOUND);
        for (FeaturesAndTests.FeatureCategory availableFeatureCategory : FeaturesAndTests.FeatureCategory.values()) {
            if (availableFeatureCategory.name() != "USABLE_SUITE") {
                String suiteName = availableFeatureCategory.name();
                builder.addLine(MESSAGE_PREFIX_CATEGORY, suiteName);
                for (FeaturesAndTests.Feature feature : availableFeatureCategory.getFeatures()) {
                    builder.addLine(MESSAGE_PREFIX_FEATURE, feature.name());
                    for (FeaturesAndTests.ScenarioClasses scenarioClasses : feature.getScenarioClasses()) {
                        builder.addLine(MESSAGE_PREFIX_SCENARIO_CLASS, scenarioClasses.name());
                    }
                }
                builder.addLine("---------------", "");
            }
        }
        builder.documentOutput(System.out::println);
    }
    //endregion

    //region GENERATE CATEGORIES FROM CATEGORIES, CLASSES OR FEATURES SPECIFIED IN THE CSV COMMAND STRING
    private List<XmlSuite> suitesFromTestClasses(String command) {

        ArrayList<String> testClasses = new ArrayList<String>(Arrays.asList(command.trim().split(COMMAND_SEPARATOR)));

        Map<String, String> testParams = new HashMap<>();
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
        XmlSuite mySuite = getSuite("Assorted Scenarios");
        List<XmlTest> myTests = new ArrayList<>();

        for (int i = 0; i < testClasses.size(); i++) {

            Boolean testClassFound = false;
            String singleTestClass = testClasses.get(i).trim();
            builder.addLine(CHECKING_SCENARIO_CLASS_AVAILABILITY, singleTestClass, DateAndTime.getCurrentTimeStampZoneBased());

            //Get all the test classes
            List<FeaturesAndTests.ScenarioClasses> existingScenarioClasses = new ArrayList<FeaturesAndTests.ScenarioClasses>(Arrays.asList(FeaturesAndTests.ScenarioClasses.values()));

            for (FeaturesAndTests.ScenarioClasses scenarioClass : existingScenarioClasses) {

                //Check if the test class matches what has been supplied
                if (singleTestClass.equals(scenarioClass.name().toString())) {
                    testClassFound = true;
                    List<XmlClass> myClasses = new ArrayList<>();
                    myClasses.add(new XmlClass(scenarioClass.getScenarioTestClass()));
                    //In this case the Scenario's
                    String testName = scenarioClass.name().toString().replace("_", " ");
                    XmlTest myTest = getTest("TEST - " + testName, mySuite, testParams, myClasses);

                    if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getParallel().equals("true")){
                        mySuite.setParallel(XmlSuite.ParallelMode.TESTS);
                        mySuite.setThreadCount(Integer.valueOf(ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getThreadCount()));
                    }

                    myTests.add(myTest);
                }
            }


            if (testClassFound) {
                builder.addLine(SCENARIO_CLASS_FOUND, singleTestClass, DateAndTime.getCurrentTimeStampZoneBased());
            } else {
                builder.addLine(SCENARIO_CLASS_NOT_FOUND, singleTestClass, DateAndTime.getCurrentTimeStampZoneBased());
            }
            builder.ConsoleOutput(System.out::println);
        }


        if (mySuite.getTests().size() > 0) {
            mySuites.add(mySuite);
            return mySuites;
        } else {
            noMatchingTests();
            return null;
        }
    }

    private List<XmlSuite> suitesFromTests(String command) {

        ArrayList<String> tests = new ArrayList<String>(Arrays.asList(command.trim().split(COMMAND_SEPARATOR)));
        Map<String, String> testParams = new HashMap<>();
        XmlSuite mySuite = getSuite("Assorted Tests");
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
        List<XmlTest> myTests = new ArrayList<>();

        for (int i = 0; i < tests.size(); i++) {

            Boolean testFound = false;
            String singleTest = tests.get(i).trim();
            builder.addLine(CHECKING_FEATURE_AVAILABILITY, singleTest, DateAndTime.getCurrentTimeStampZoneBased());

            //Get All existing suites
            List<FeaturesAndTests.FeatureCategory> existingCategories = new ArrayList<FeaturesAndTests.FeatureCategory>(Arrays.asList(FeaturesAndTests.FeatureCategory.values()));

            for (FeaturesAndTests.FeatureCategory featureCategory : existingCategories) {

                //Get all the Tests in each featureCategory
                for (FeaturesAndTests.Feature feature : featureCategory.getFeatures()) {

                    if (feature.name().toString().equals(singleTest)) {

                        testFound = true;

                        List<XmlClass> myClasses = new ArrayList<>();

                        for (FeaturesAndTests.ScenarioClasses scenarioClasses : feature.getScenarioClasses()) {
                            myClasses.add(new XmlClass(scenarioClasses.getScenarioTestClass()));
                        }

                        XmlTest myTest = getTest(feature.getTestName().replace("_", " "), mySuite, testParams, myClasses);

                        if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getParallel().equals("true")){
                            myTest.setParallel(XmlSuite.ParallelMode.CLASSES);
                            myTest.setThreadCount(Integer.valueOf(ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getThreadCount()));
                        }
                        myTests.add(myTest);
                    }
                }
            }
            if (testFound) {
                builder.addLine(FEATURE_FOUND, singleTest, DateAndTime.getCurrentTimeStampZoneBased());
            } else {
                builder.addLine(FEATURE_NOT_FOUND, singleTest, DateAndTime.getCurrentTimeStampZoneBased());
            }
        }

        if(myTests.size()>0){
            if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getParallel().equals("true")){
                mySuite.setParallel(XmlSuite.ParallelMode.TESTS);
                mySuite.setThreadCount(Integer.valueOf(ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getThreadCount()));
            }
            mySuites.add(mySuite);
        }
        builder.ConsoleOutput(System.out::println);

        if (mySuites.size() > 0) {
            return mySuites;
        } else {
            noMatchingTests();
            return null;
        }
    }

    private List<XmlSuite> suitesFromSuites(String command) {

        ArrayList<String> suiteNames = new ArrayList<String>(Arrays.asList(command.trim().split(COMMAND_SEPARATOR)));
        Map<String, String> testParams = new HashMap<>();
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();

        for (int i = 0; i < suiteNames.size(); i++) {
            String suiteName = suiteNames.get(i).trim().toUpperCase();
            Boolean suiteFound = false;
            builder.addLine(CHECKING_CATEGORY_AVAILABILITY, suiteName, DateAndTime.getCurrentTimeStampZoneBased());

            //Get All existing suites
            List<FeaturesAndTests.FeatureCategory> existingCategories = new ArrayList<FeaturesAndTests.FeatureCategory>(Arrays.asList(FeaturesAndTests.FeatureCategory.values()));

            for (FeaturesAndTests.FeatureCategory featureCategory : existingCategories) {
                if (suiteName.trim().equals(featureCategory.name().toString())) {
                    suiteFound = true;

                    XmlSuite mySuite = getSuite(featureCategory.getCategoryDescription());
                    List<XmlTest> myTests = new ArrayList<>();

                    for (FeaturesAndTests.Feature feature : featureCategory.getFeatures()) {
                        List<XmlClass> myClasses = new ArrayList<>();

                        for (FeaturesAndTests.ScenarioClasses scenarioClasses : feature.getScenarioClasses()) {
                            myClasses.add(new XmlClass(scenarioClasses.getScenarioTestClass()));
                        }
                        XmlTest myTest = getTest(feature.getTestName(), mySuite, testParams, myClasses);
                        if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getParallel().equals("true")){
                            myTest.setParallel(XmlSuite.ParallelMode.CLASSES);
                            myTest.setThreadCount(Integer.valueOf(ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getThreadCount()));
                        }
                        myTests.add(myTest);
                    }
                    if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getParallel().equals("true")){
                        mySuite.setParallel(XmlSuite.ParallelMode.TESTS);
                        mySuite.setThreadCount(Integer.valueOf(ConfigHelper.TEST_EXECUTION_CONFIGURATION.getParallelExecution().getThreadCount()));
                    }
                    mySuites.add(mySuite);
                }
            }
            if (suiteFound) {
                builder.addLine(CATEGORY_FOUND, suiteName, DateAndTime.getCurrentTimeStampZoneBased());
            } else {
                builder.addLine(CATEGORY_NOT_FOUND, suiteName, DateAndTime.getCurrentTimeStampZoneBased());
            }
        }
        builder.ConsoleOutput(System.out::println);
        if (mySuites.size() > 0) {
            return mySuites;
        } else {
            noMatchingTests();
            return null;
        }
    }
    //endregion

    //region INITIALIZE AND RETURN TEST NG ARTIFACTS
    private XmlSuite getSuite(String suiteName) {
        XmlSuite suite = new XmlSuite();
        suite.setName(suiteName);
        suite.addListener(REPORTNG_HTML_REPORTER);
        return suite;
    }

    private XmlTest getTest(String testName, XmlSuite suite, Map<String, String> testParams, List<XmlClass> scenarioClasses) {
        XmlTest test = new XmlTest(suite);
        test.setName(testName);
        test.setParameters(testParams);
        test.setClasses(scenarioClasses);
        return test;
    }

    private TestNG getTestNG(List<XmlSuite> suites, TestListenerAdapter testListenerAdapter, Boolean useDefaultListeners) throws InterruptedException {

        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.addListener(testListenerAdapter);
        tng.setUseDefaultListeners(useDefaultListeners);
        return tng;
    }
    //endregion

    //region RUN THE FEATURES
    private void runTest(List<XmlSuite> suites) throws Exception {

        TestListenerAdapter tla = new TestListenerAdapter();
        executeTest(suites, tla);
    }

    private void executeTest(List<XmlSuite> suites, TestListenerAdapter tla) throws Exception {

        TestNG tng = getTestNG(suites, tla, true);
        tng.run();

        /*if( ConfigHelper.TEST_EXECUTION_CONFIGURATION.getEnableAlerts().equals("true")){
            MailerService.setAlertForSuiteList();
            if (tng.hasFailure()) {
                sendAlert(false);
            } else {
                sendAlert(true);
            }
        }*/
    }
    //endregion
}
