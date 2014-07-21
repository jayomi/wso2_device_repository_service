package org.wso2.repository.device.data;

public class DeviceType
{
    
    String deviceTypeId;
    String deviceTypeName;
    String deviceTypeDescription;

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceTypeDescription() {
        return deviceTypeDescription;
    }

    public void setDeviceTypeDescription(String deviceTypeDescription) {
        this.deviceTypeDescription = deviceTypeDescription;
    }
}