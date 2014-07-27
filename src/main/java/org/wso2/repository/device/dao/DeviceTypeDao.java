package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.DeviceType;

import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;

/**
 * Created by jayomi on 7/27/14.
 */
public interface DeviceTypeDao {

    public String deleteDeviceType(String id) throws Exception;
    public LinkedList<DeviceType> getDeviceTypes(UriInfo parameters);
    public String addDevice(DeviceType deviceType);
    public String updateDevice(DeviceType deviceType,String id);
}
