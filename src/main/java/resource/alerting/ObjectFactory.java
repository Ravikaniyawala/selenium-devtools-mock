
package resource.alerting;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the resources.alerting package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SuiteListID_QNAME = new QName("", "SuiteListID");
    private final static QName _Password_QNAME = new QName("", "password");
    private final static QName _Email_QNAME = new QName("", "Email");
    private final static QName _CcEmail_QNAME = new QName("", "ccEmail");
    private final static QName _Subject_QNAME = new QName("", "subject");
    private final static QName _Message_QNAME = new QName("", "message");
    private final static QName _UserName_QNAME = new QName("", "userName");
    private final static QName _RecipientEmail_QNAME = new QName("", "recipientEmail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: resources.alerting
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Recipients }
     * 
     */
    public Recipients createRecipients() {
        return new Recipients();
    }

    /**
     * Create an instance of {@link PassedTests }
     * 
     */
    public PassedTests createPassedTests() {
        return new PassedTests();
    }

    /**
     * Create an instance of {@link AlertsInfo }
     * 
     */
    public AlertsInfo createAlertsInfo() {
        return new AlertsInfo();
    }

    /**
     * Create an instance of {@link Sender }
     * 
     */
    public Sender createSender() {
        return new Sender();
    }

    /**
     * Create an instance of {@link AlertContent }
     * 
     */
    public AlertContent createAlertContent() {
        return new AlertContent();
    }

    /**
     * Create an instance of {@link FailedTests }
     * 
     */
    public FailedTests createFailedTests() {
        return new FailedTests();
    }

    /**
     * Create an instance of {@link Alert }
     * 
     */
    public Alert createAlert() {
        return new Alert();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "SuiteListID")
    public JAXBElement<String> createSuiteListID(String value) {
        return new JAXBElement<String>(_SuiteListID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "password")
    public JAXBElement<String> createPassword(String value) {
        return new JAXBElement<String>(_Password_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Email")
    public JAXBElement<String> createEmail(String value) {
        return new JAXBElement<String>(_Email_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ccEmail")
    public JAXBElement<String> createCcEmail(String value) {
        return new JAXBElement<String>(_CcEmail_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "subject")
    public JAXBElement<String> createSubject(String value) {
        return new JAXBElement<String>(_Subject_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "message")
    public JAXBElement<String> createMessage(String value) {
        return new JAXBElement<String>(_Message_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "userName")
    public JAXBElement<String> createUserName(String value) {
        return new JAXBElement<String>(_UserName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "recipientEmail")
    public JAXBElement<String> createRecipientEmail(String value) {
        return new JAXBElement<String>(_RecipientEmail_QNAME, String.class, null, value);
    }

}
