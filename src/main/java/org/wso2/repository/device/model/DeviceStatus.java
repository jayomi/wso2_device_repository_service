package org.wso2.repository.device.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DeviceStatus")
public class DeviceStatus
{
    
    String deviceStatusId;
    String deviceStatusName;

    public String getDeviceStatusId() {
        return deviceStatusId;
    }

    @XmlElement(name="deviceStatusId")
    public void setDeviceStatusId(String deviceStatusId) {
        this.deviceStatusId = deviceStatusId;
    }

    public String getDeviceStatusName() {
        return deviceStatusName;
    }

    @XmlElement(name="deviceStatusName")
    public void setDeviceStatusName(String deviceStatusName) {
        this.deviceStatusName = deviceStatusName;
    }
}