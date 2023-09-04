
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
 *         &lt;element ref="{}Parallel"/>
 *         &lt;element ref="{}ThreadCount"/>
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
    "parallel",
    "threadCount"
})
@XmlRootElement(name = "ParallelExecution")
public class ParallelExecution {

    @XmlElement(name = "Parallel", required = true)
    protected String parallel;
    @XmlElement(name = "ThreadCount", required = true)
    protected String threadCount;

    /**
     * Gets the value of the parallel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParallel() {
        return parallel;
    }

    /**
     * Sets the value of the parallel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParallel(String value) {
        this.parallel = value;
    }

    /**
     * Gets the value of the threadCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThreadCount() {
        return threadCount;
    }

    /**
     * Sets the value of the threadCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThreadCount(String value) {
        this.threadCount = value;
    }

}
