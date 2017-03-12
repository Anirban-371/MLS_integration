package rets1_5;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"reData"
})
@XmlRootElement(name = "RETS")
public class RETS {
	
	@XmlElement(name = "REData")
    protected REData reData;

	public REData getReData() {
		return reData;
	}

	public void setReData(REData reData) {
		this.reData = reData;
	}
	
	
    
    
    
   
}
