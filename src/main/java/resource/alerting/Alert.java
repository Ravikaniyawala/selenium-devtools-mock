
package resource.alerting;

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
 *         &lt;element ref="{}SuiteListID"/>
 *         &lt;element ref="{}recipientEmail"/>
 *         &lt;element ref="{}ccEmail"/>
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
    "suiteListID",
    "recipientEmail",
    "ccEmail"
})
@XmlRootElement(name = "Alert")
public class Alert {

    @XmlElement(name = "SuiteListID", required = true)
    protected String suiteListID;
    @XmlElement(required = true)
    protected String recipientEmail;
    @XmlElement(required = true)
    protected String ccEmail;

    /**
     * Gets the value of the suiteListID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuiteListID() {
        return suiteListID;
    }

    /**
     * Sets the value of the suiteListID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuiteListID(String value) {
        this.suiteListID = value;
    }

    /**
     * Gets the value of the recipientEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Sets the value of the recipientEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientEmail(String value) {
        this.recipientEmail = value;
    }

    /**
     * Gets the value of the ccEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcEmail() {
        return ccEmail;
    }

    /**
     * Sets the value of the ccEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcEmail(String value) {
        this.ccEmail = value;
    }

}
