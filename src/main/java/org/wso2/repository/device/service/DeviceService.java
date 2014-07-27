package org.wso2.repository.device.service;

import org.wso2.repository.device.dao.DeviceDao;
import org.wso2.repository.device.dao.DeviceDaoImpl;
import org.wso2.repository.device.model.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.LinkedList;


@Path("/device/")
public class DeviceService
{

    DeviceDao deviceDao;


    @DELETE
    @Path("/deletedevice/{id}/")
    public Response deleteDevice(@PathParam("id") String id) throws Exception {

      String strResponse;
      deviceDao=new DeviceDaoImpl();
      strResponse=deviceDao.deleteDevice(id);
      return Response.ok(strResponse).build();

    }

    @GET
    @Path("/getdevices/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<Device> getDevices(@Context UriInfo parameters) throws SQLException {
        LinkedList deviceList=new LinkedList();
        deviceDao=new DeviceDaoImpl();
        deviceList=deviceDao.getDevices(parameters);
        return deviceList;

    }

    @POST
    @Path("/adddevice/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDevice(Device device) throws SQLException {

        String strResponse="";
        deviceDao=new DeviceDaoImpl();
        strResponse=deviceDao.addDevice(device);
        return Response.ok(strResponse).build();

    }


    @PUT
    @Path("/updatedevice/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDevice(Device device ,@PathParam("id") String id ) throws SQLException {

        String strResponse="";
        deviceDao=new DeviceDaoImpl();
        strResponse=deviceDao.updateDevice(device,id);
        return Response.ok(strResponse).build();


    }



}