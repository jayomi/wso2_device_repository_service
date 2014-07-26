package org.wso2.repository.device.dao;


import org.wso2.repository.device.model.DeviceStatus;

import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;

public interface DeviceStatusDao {

    public String deleteDeviceStatus(String id) throws Exception;
    public LinkedList<DeviceStatus> getDevices(UriInfo parameters);
    public String addDevice(DeviceStatus deviceStatus);
    public String updateDevice(DeviceStatus  device,String id);


}
