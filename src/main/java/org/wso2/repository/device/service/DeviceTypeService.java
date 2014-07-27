package org.wso2.repository.device.service;

import org.wso2.repository.device.dao.DeviceTypeDao;
import org.wso2.repository.device.dao.DeviceTypeDaoImple;
import org.wso2.repository.device.model.DeviceType;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.LinkedList;


@Path("/devicetype/")
public class DeviceTypeService
{
    DeviceTypeDao deviceTypeDao;

    @DELETE
    @Path("/deletedevicetype/{id}/")
    public Response deleteDevice(@PathParam("id") String id) throws Exception {

        String strResponse;
        deviceTypeDao=new DeviceTypeDaoImple();
        strResponse=deviceTypeDao.deleteDeviceType(id);
        return Response.ok(strResponse).build();

    }


    @GET
    @Path("/getdevicetypes/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<DeviceType> getDevices(@Context UriInfo parameters)throws Exception {

        LinkedList<DeviceType> deviceTypeList = new LinkedList<DeviceType>();
        deviceTypeDao=new DeviceTypeDaoImple();
        deviceTypeList = deviceTypeDao.getDeviceTypes(parameters);
        return deviceTypeList;

    }


    @POST
    @Path("/adddevicetype/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDevice(DeviceType deviceType) throws SQLException {

        String strResponse="";
        deviceTypeDao=new DeviceTypeDaoImple();
        strResponse=deviceTypeDao.addDevice(deviceType);
        return Response.ok(strResponse).build();


    }


    @PUT
    @Path("/updatedevicetype/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDevice(DeviceType deviceType ,@PathParam("id") String id ) throws SQLException {

        String strResponse="";
        deviceTypeDao=new DeviceTypeDaoImple();
        strResponse=deviceTypeDao.updateDevice(deviceType,id);
        return Response.ok(strResponse).build();

    }
    

}