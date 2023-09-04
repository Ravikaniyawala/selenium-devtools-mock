
package resource.configuration;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}DocumentationOptions"/>
 *         &lt;element ref="{}Documentation"/>
 *         &lt;element ref="{}ActionOptions"/>
 *         &lt;element ref="{}Action"/>
 *         &lt;element ref="{}CommandPrefixes"/>
 *         &lt;element ref="{}Environments"/>
 *         &lt;element ref="{}RunAgainst"/>
 *         &lt;element ref="{}EnableAlerts"/>
 *         &lt;element ref="{}AvailableBrowsers"/>
 *         &lt;element ref="{}Browser"/>
 *         &lt;element ref="{}ParallelExecution"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "documentationOptions",
    "documentation",
    "actionOptions",
    "action",
    "commandPrefixes",
    "environments",
    "runAgainst",
    "enableAlerts",
    "availableBrowsers",
    "browser",
    "parallelExecution"
})
@XmlRootElement(name = "Configuration")
public class Configuration {

    @XmlElement(name = "DocumentationOptions", required = true)
    protected DocumentationOptions documentationOptions;
    @XmlElement(name = "Documentation", required = true)
    protected String documentation;
    @XmlElement(name = "ActionOptions", required = true)
    protected ActionOptions actionOptions;
    @XmlElement(name = "Action", required = true)
    protected String action;
    @XmlElement(name = "CommandPrefixes", required = true)
    protected CommandPrefixes commandPrefixes;
    @XmlElement(name = "Environments", required = true)
    protected Environments environments;
    @XmlElement(name = "RunAgainst", required = true)
    protected RunAgainst runAgainst;
    @XmlElement(name = "EnableAlerts", required = true)
    protected String enableAlerts;
    @XmlElement(name = "AvailableBrowsers", required = true)
    protected AvailableBrowsers availableBrowsers;
    @XmlElement(name = "Browser", required = true)
    protected String browser;
    @XmlElement(name = "ParallelExecution", required = true)
    protected ParallelExecution parallelExecution;

    /**
     * Documentation Options
     * 
     * @return
     *     possible object is
     *     {@link DocumentationOptions }
     *     
     */
    public DocumentationOptions getDocumentationOptions() {
        return documentationOptions;
    }

    /**
     * Sets the value of the documentationOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentationOptions }
     *     
     */
    public void setDocumentationOptions(DocumentationOptions value) {
        this.documentationOptions = value;
    }

    /**
     * Documentation can have the following ALL, INFO, SCENARIO
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * Sets the value of the documentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentation(String value) {
        this.documentation = value;
    }

    /**
     * Action Options
     * 
     * @return
     *     possible object is
     *     {@link ActionOptions }
     *     
     */
    public ActionOptions getActionOptions() {
        return actionOptions;
    }

    /**
     * Sets the value of the actionOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionOptions }
     *     
     */
    public void setActionOptions(ActionOptions value) {
        this.actionOptions = value;
    }

    /**
     * Action can have the following DOCUMENT, TEST
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * The CSV String must start with one of these command prefixes
     * 
     * @return
     *     possible object is
     *     {@link CommandPrefixes }
     *     
     */
    public CommandPrefixes getCommandPrefixes() {
        return commandPrefixes;
    }

    /**
     * Sets the value of the commandPrefixes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommandPrefixes }
     *     
     */
    public void setCommandPrefixes(CommandPrefixes value) {
        this.commandPrefixes = value;
    }

    /**
     * The available environments
     * 
     * @return
     *     possible object is
     *     {@link Environments }
     *     
     */
    public Environments getEnvironments() {
        return environments;
    }

    /**
     * Sets the value of the environments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Environments }
     *     
     */
    public void setEnvironments(Environments value) {
        this.environments = value;
    }

    /**
     * Environment that the test must run against
     * 
     * @return
     *     possible object is
     *     {@link RunAgainst }
     *     
     */
    public RunAgainst getRunAgainst() {
        return runAgainst;
    }

    /**
     * Sets the value of the runAgainst property.
     * 
     * @param value
     *     allowed object is
     *     {@link RunAgainst }
     *     
     */
    public void setRunAgainst(RunAgainst value) {
        this.runAgainst = value;
    }

    /**
     * Alerts Flag
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnableAlerts() {
        return enableAlerts;
    }

    /**
     * Sets the value of the enableAlerts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnableAlerts(String value) {
        this.enableAlerts = value;
    }

    /**
     * The available Browsers the test can run on
     * 
     * @return
     *     possible object is
     *     {@link AvailableBrowsers }
     *     
     */
    public AvailableBrowsers getAvailableBrowsers() {
        return availableBrowsers;
    }

    /**
     * Sets the value of the availableBrowsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailableBrowsers }
     *     
     */
    public void setAvailableBrowsers(AvailableBrowsers value) {
        this.availableBrowsers = value;
    }

    /**
     * Browser To be used for Testing
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * Sets the value of the browser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrowser(String value) {
        this.browser = value;
    }

    /**
     * Parallel Execution of Tests
     * 
     * @return
     *     possible object is
     *     {@link ParallelExecution }
     *     
     */
    public ParallelExecution getParallelExecution() {
        return parallelExecution;
    }

    /**
     * Sets the value of the parallelExecution property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParallelExecution }
     *     
     */
    public void setParallelExecution(ParallelExecution value) {
        this.parallelExecution = value;
    }

}
