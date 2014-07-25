package org.wso2.repository.device.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DeviceType")
public class DeviceType
{
    
    String deviceTypeId;
    String deviceTypeName;
    String deviceTypeDescription;

    @XmlElement(name="deviceTypeId")
    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    @XmlElement(name="deviceTypeName")
    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    @XmlElement(name="deviceTypeDescription")
    public String getDeviceTypeDescription() {
        return deviceTypeDescription;
    }

    public void setDeviceTypeDescription(String deviceTypeDescription) {
     this.deviceTypeDescription=deviceTypeDescription;
    }
}