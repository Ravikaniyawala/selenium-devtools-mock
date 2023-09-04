/*
package reporting;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.annotations.AfterSuite;
import resources.alerting.AlertsInfo;
import resources.alerting.Recipients;
import utilities.FileHelpers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static reporting.MailerService.Config.OUTLOOK_365;

public class MailerService {

    public MailerService() {
    }

    private static Document document = new Document();

    //region STATIC STRINGS
    private final static String ALERT_CONFIGURATION_UNAVAILABLE = "The alert TEST_EXECUTION_CONFIGURATION file could not be found.";
    private final static String USING_DEFAULT_NOTIFICATION_ALERT = "Using default alert email id: ";
    private final static String ALERTS_CONFIGURATION_PATH = "/configurations/Alerts.xml";
    private final static String ALERT_TRIGGERED = "Alert Triggered";
    private final static String ATTACHMENT_DESCRIPTION = "TestReport.html";
    private final static String ATTACHMENT_NAME = "Report";
    private final static String SYS_DIRECTORY = "user.dir";
    private final static String FORWARD_SLASH = "/";
    private final static String BACK_SLASH = "\\";
    private final static String TEST_OUTPUT = "/test-output/emailable-report.html";
    private static AlertsInfo alertsInfo;
    public static final String INVALID_EMAIL_ADDRESS_RECIPIENTS = "The recipients email address is invalid: ";
    //endregion

    @AfterSuite
    public static void sendAlert( Boolean testsPassed) throws Exception {
        System.out.println("Sending test results as an email");

        String attachmentPath = System.getProperty(SYS_DIRECTORY) + TEST_OUTPUT;
        String filePath = FORWARD_SLASH + attachmentPath.replace(BACK_SLASH, FORWARD_SLASH);

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(OUTLOOK_365.getSmtpsHost());
        email.setSmtpPort(Integer.valueOf(OUTLOOK_365.getSmtpPort()));
        email.setAuthenticator(new DefaultAuthenticator(alertsInfo.getSender().getUserName() + OUTLOOK_365.getMailDomain(), alertsInfo.getSender().getPassword()));
        email.setStartTLSEnabled(true);
        email.setFrom(alertsInfo.getSender().getUserName() + OUTLOOK_365.getMailDomain());
        email.setDebug(false);

        if (!testsPassed){
            email.setSubject(alertsInfo.getAlertContent().getFailedTests().getSubject());
            email.setMsg(alertsInfo.getAlertContent().getFailedTests().getMessage());
        }else{
            email.setSubject(alertsInfo.getAlertContent().getPassedTests().getSubject());
            email.setMsg(alertsInfo.getAlertContent().getPassedTests().getMessage());
        }

        if (validatedMailAddress(alertsInfo.getRecipients().getEmail())){
            email.addTo(alertsInfo.getRecipients().getEmail());
        }else{
            throw new Exception(INVALID_EMAIL_ADDRESS_RECIPIENTS + alertsInfo.getRecipients().getEmail());
        }

        if (new File(filePath).exists()) {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(new File(filePath).getAbsolutePath());
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription(ATTACHMENT_DESCRIPTION);
            attachment.setName(ATTACHMENT_NAME);
            email.attach(attachment);
        }
        email.send();
        document.output(ALERT_TRIGGERED);
    }


    public static void setAlertForSuiteList() throws Exception {

        File file = FileHelpers.getFileFromRelativePath(ALERTS_CONFIGURATION_PATH);

        if(!file.exists()) {
            throw new Exception(ALERT_CONFIGURATION_UNAVAILABLE + file.getName());
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(AlertsInfo.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        alertsInfo = (AlertsInfo) unmarshaller.unmarshal(file);

        document.display(USING_DEFAULT_NOTIFICATION_ALERT + alertsInfo.getSender().getUserName() + OUTLOOK_365.getMailDomain());
        Recipients allRecipients = alertsInfo.getRecipients();
        allRecipients.setEmail(allRecipients.getEmail() + alertsInfo.getSender().getUserName() + OUTLOOK_365.getMailDomain());
        alertsInfo.setRecipients(allRecipients);
    }

    private static Boolean validatedMailAddress(String email){
        return  EmailValidator.getInstance().isValid(email);
    }

    //region MAILER CONFIGURATIONS
    public enum Config {

        OUTLOOK_365(
                "smtp.office365.com",
                "587",
                "@foodstuffs.co.nz"
        );

        private String smtpsHost;
        private String smtpPort;
        private String mailDomain;

        Config(String smtpsHost, String smtpPort, String mailDomain) {
            this.smtpsHost = smtpsHost;
            this.smtpPort = smtpPort;
            this.mailDomain = mailDomain;
        }

        public String getSmtpsHost() {
            return smtpsHost;
        }

        public String getSmtpPort() {
            return smtpPort;
        }

        public String getMailDomain() {
            return mailDomain;
        }
    }
    //endregion
}
*/
