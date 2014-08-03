package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.Device;

import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;

public interface DeviceDao {

    public String deleteDevice(String id) throws Exception;
    public LinkedList<Device> getDevices(UriInfo parameters);
    public LinkedList<Device> getDevicesDetail(UriInfo parameters);
    public String addDevice(Device device);
    public String updateDevice(Device device,String id);

}
