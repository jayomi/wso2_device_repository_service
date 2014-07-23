package org.wso2.repository.device.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Device")
public class Device
{
    
    String deviceId;
    String deviceName;
    String deviceDescription;
    String statusId;

}