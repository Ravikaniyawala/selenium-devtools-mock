
package resource.configuration;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the resources.configuration package. 
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

    private final static QName _Action_QNAME = new QName("", "Action");
    private final static QName _ThreadCount_QNAME = new QName("", "ThreadCount");
    private final static QName _Documentation_QNAME = new QName("", "Documentation");
    private final static QName _EnableAlerts_QNAME = new QName("", "EnableAlerts");
    private final static QName _AvailableBrowser_QNAME = new QName("", "AvailableBrowser");
    private final static QName _DocumentationOption_QNAME = new QName("", "DocumentationOption");
    private final static QName _Environment_QNAME = new QName("", "Environment");
    private final static QName _Parallel_QNAME = new QName("", "Parallel");
    private final static QName _Prefix_QNAME = new QName("", "Prefix");
    private final static QName _ActionOption_QNAME = new QName("", "ActionOption");
    private final static QName _Browser_QNAME = new QName("", "Browser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: resources.configuration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ParallelExecution }
     * 
     */
    public ParallelExecution createParallelExecution() {
        return new ParallelExecution();
    }

    /**
     * Create an instance of {@link AvailableBrowsers }
     * 
     */
    public AvailableBrowsers createAvailableBrowsers() {
        return new AvailableBrowsers();
    }

    /**
     * Create an instance of {@link Configuration }
     * 
     */
    public Configuration createConfiguration() {
        return new Configuration();
    }

    /**
     * Create an instance of {@link DocumentationOptions }
     * 
     */
    public DocumentationOptions createDocumentationOptions() {
        return new DocumentationOptions();
    }

    /**
     * Create an instance of {@link ActionOptions }
     * 
     */
    public ActionOptions createActionOptions() {
        return new ActionOptions();
    }

    /**
     * Create an instance of {@link CommandPrefixes }
     * 
     */
    public CommandPrefixes createCommandPrefixes() {
        return new CommandPrefixes();
    }

    /**
     * Create an instance of {@link Environments }
     * 
     */
    public Environments createEnvironments() {
        return new Environments();
    }

    /**
     * Create an instance of {@link RunAgainst }
     * 
     */
    public RunAgainst createRunAgainst() {
        return new RunAgainst();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Action")
    public JAXBElement<String> createAction(String value) {
        return new JAXBElement<String>(_Action_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ThreadCount")
    public JAXBElement<String> createThreadCount(String value) {
        return new JAXBElement<String>(_ThreadCount_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Documentation")
    public JAXBElement<String> createDocumentation(String value) {
        return new JAXBElement<String>(_Documentation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "EnableAlerts")
    public JAXBElement<String> createEnableAlerts(String value) {
        return new JAXBElement<String>(_EnableAlerts_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "AvailableBrowser")
    public JAXBElement<String> createAvailableBrowser(String value) {
        return new JAXBElement<String>(_AvailableBrowser_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DocumentationOption")
    public JAXBElement<String> createDocumentationOption(String value) {
        return new JAXBElement<String>(_DocumentationOption_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Environment")
    public JAXBElement<String> createEnvironment(String value) {
        return new JAXBElement<String>(_Environment_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Parallel")
    public JAXBElement<String> createParallel(String value) {
        return new JAXBElement<String>(_Parallel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Prefix")
    public JAXBElement<String> createPrefix(String value) {
        return new JAXBElement<String>(_Prefix_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ActionOption")
    public JAXBElement<String> createActionOption(String value) {
        return new JAXBElement<String>(_ActionOption_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Browser")
    public JAXBElement<String> createBrowser(String value) {
        return new JAXBElement<String>(_Browser_QNAME, String.class, null, value);
    }

}
