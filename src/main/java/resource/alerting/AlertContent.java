
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
 *         &lt;element ref="{}FailedTests"/>
 *         &lt;element ref="{}PassedTests"/>
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
    "failedTests",
    "passedTests"
})
@XmlRootElement(name = "AlertContent")
public class AlertContent {

    @XmlElement(name = "FailedTests", required = true)
    protected FailedTests failedTests;
    @XmlElement(name = "PassedTests", required = true)
    protected PassedTests passedTests;

    /**
     * Gets the value of the failedTests property.
     * 
     * @return
     *     possible object is
     *     {@link FailedTests }
     *     
     */
    public FailedTests getFailedTests() {
        return failedTests;
    }

    /**
     * Sets the value of the failedTests property.
     * 
     * @param value
     *     allowed object is
     *     {@link FailedTests }
     *     
     */
    public void setFailedTests(FailedTests value) {
        this.failedTests = value;
    }

    /**
     * Gets the value of the passedTests property.
     * 
     * @return
     *     possible object is
     *     {@link PassedTests }
     *     
     */
    public PassedTests getPassedTests() {
        return passedTests;
    }

    /**
     * Sets the value of the passedTests property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassedTests }
     *     
     */
    public void setPassedTests(PassedTests value) {
        this.passedTests = value;
    }

}
