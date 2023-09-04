package testExecution;

import resource.configuration.Configuration;
import utilities.FileHelpers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ConfigHelper {

    public static Configuration TEST_EXECUTION_CONFIGURATION;
    private final static String TEST_ARTIFACT_CONFIGURATION_PATH = "/configurations/Configuration.xml";
    private final static String SERVICE_RESOURCE_FOLDER_DOES_NOT_EXIST = "The resource file does not exist: ";
    private final static String UN_RECOGNIZED_ENVIRONMENT = "Check Configuration File - Unable to recognize the Environment to run Feature against: ";
    private final static String UN_RECOGNIZED_DOCUMENTATION_OPTION = "Check Configuration File - Unable to recognize the Documentation Option: ";
    private final static String UN_RECOGNIZED_ACTION_OPTION = "Check Configuration File - Unable to recognize the Action Option: ";
    private final static String UN_RECOGNIZED_BROWSER_OPTION = "Check Configuration File - Unable to recognize the Browser Option: ";
    private final static String UN_RECOGNIZED_ALERT_OPTION = "Check Configuration File - Unable to recognize the Alerts Option: ";
    private final static String DEV = "DEV";
    private final static String QA = "QA";
    private final static String INT = "INT";
    private final static String PRE_PROD = "PRE-PROD";


    public static void setResources() throws Exception {

        File file = FileHelpers.getFileFromRelativePath(TEST_ARTIFACT_CONFIGURATION_PATH);

        if(!file.exists()) {
            throw new Exception(SERVICE_RESOURCE_FOLDER_DOES_NOT_EXIST + file.getName());
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        TEST_EXECUTION_CONFIGURATION = (Configuration) unmarshaller.unmarshal(file);

        if (!TEST_EXECUTION_CONFIGURATION.getEnvironments().getEnvironment().contains(TEST_EXECUTION_CONFIGURATION.getRunAgainst().getEnvironment())){
            throw new Exception(UN_RECOGNIZED_ENVIRONMENT + TEST_EXECUTION_CONFIGURATION.getRunAgainst().getEnvironment());
        }

        if (!TEST_EXECUTION_CONFIGURATION.getDocumentationOptions().getDocumentationOption().contains(TEST_EXECUTION_CONFIGURATION.getDocumentation())){
            throw new Exception(UN_RECOGNIZED_DOCUMENTATION_OPTION + TEST_EXECUTION_CONFIGURATION.getDocumentation());
        }

        if (!TEST_EXECUTION_CONFIGURATION.getActionOptions().getActionOption().contains(TEST_EXECUTION_CONFIGURATION.getAction())){
            throw new Exception(UN_RECOGNIZED_ACTION_OPTION + TEST_EXECUTION_CONFIGURATION.getAction());
        }

        if (!TEST_EXECUTION_CONFIGURATION.getAvailableBrowsers().getAvailableBrowser().contains(TEST_EXECUTION_CONFIGURATION.getBrowser())){
            throw new Exception(UN_RECOGNIZED_BROWSER_OPTION + TEST_EXECUTION_CONFIGURATION.getBrowser());
        }

        if(!TEST_EXECUTION_CONFIGURATION.getEnableAlerts().equals("true") && !TEST_EXECUTION_CONFIGURATION.getEnableAlerts().equals("false")){
            throw new Exception(UN_RECOGNIZED_ALERT_OPTION + TEST_EXECUTION_CONFIGURATION.getEnableAlerts());
        }
    }
}
