package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.Device;

import javax.ws.rs.core.Response;
import java.util.LinkedList;

/**
 * Created by jayomi on 7/25/14.
 */
public interface DeviceDao {


    public String deleteDevice(String id);
    //public Device getDevice();
    public LinkedList<Device> getAllDevice();
    public LinkedList<Device> searchDevice();
    public String addDevice(Device device);
    public Response updateDevice(Device device);
}
