package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.Device;

import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;

public interface DeviceDao {

    public String deleteDevice(String id);
    public Device getDevice();
    public LinkedList<Device> getDevices();
    public LinkedList<Device> searchDevice(UriInfo parameters);
    public String addDevice(Device device);
    public String updateDevice(Device device,String id);

}
