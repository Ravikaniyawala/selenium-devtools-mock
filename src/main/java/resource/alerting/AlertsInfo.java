
package resource.alerting;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element ref="{}Sender"/>
 *         &lt;element ref="{}AlertContent"/>
 *         &lt;element ref="{}Recipients"/>
 *         &lt;element ref="{}Alert" maxOccurs="unbounded" minOccurs="0"/>
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
    "sender",
    "alertContent",
    "recipients",
    "alert"
})
@XmlRootElement(name = "AlertsInfo")
public class AlertsInfo {

    @XmlElement(name = "Sender", required = true)
    protected Sender sender;
    @XmlElement(name = "AlertContent", required = true)
    protected AlertContent alertContent;
    @XmlElement(name = "Recipients", required = true)
    protected Recipients recipients;
    @XmlElement(name = "Alert")
    protected List<Alert> alert;

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link Sender }
     *     
     */
    public Sender getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sender }
     *     
     */
    public void setSender(Sender value) {
        this.sender = value;
    }

    /**
     * Gets the value of the alertContent property.
     * 
     * @return
     *     possible object is
     *     {@link AlertContent }
     *     
     */
    public AlertContent getAlertContent() {
        return alertContent;
    }

    /**
     * Sets the value of the alertContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertContent }
     *     
     */
    public void setAlertContent(AlertContent value) {
        this.alertContent = value;
    }

    /**
     * Gets the value of the recipients property.
     * 
     * @return
     *     possible object is
     *     {@link Recipients }
     *     
     */
    public Recipients getRecipients() {
        return recipients;
    }

    /**
     * Sets the value of the recipients property.
     * 
     * @param value
     *     allowed object is
     *     {@link Recipients }
     *     
     */
    public void setRecipients(Recipients value) {
        this.recipients = value;
    }

    /**
     * Gets the value of the alert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Alert }
     * 
     * 
     */
    public List<Alert> getAlerts() {
        if (alert == null) {
            alert = new ArrayList<Alert>();
        }
        return this.alert;
    }

}
