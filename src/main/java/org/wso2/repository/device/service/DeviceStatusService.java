package org.wso2.repository.device.service;

import org.wso2.repository.device.dao.DeviceStatusDao;
import org.wso2.repository.device.dao.DeviceStatusDaoImple;
import org.wso2.repository.device.model.DeviceStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.LinkedList;


@Path("/devicestatus/")
public class DeviceStatusService
{

    DeviceStatusDao statusDao;



    @DELETE
    @Path("/deletedevicestatus/{id}/")
    public Response deleteDeviceStatus(@PathParam("id") String id) throws Exception {

        String strResponse;
        statusDao=new DeviceStatusDaoImple();
        strResponse=statusDao.deleteDeviceStatus(id);
        return Response.ok(strResponse).build();

    }



    @GET
    @Path("/getdevicestatuses/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<DeviceStatus> getDeviceStatuses(@Context UriInfo parameters)throws SQLException {

        LinkedList<DeviceStatus> statusList = new LinkedList<DeviceStatus>();

        statusDao=new DeviceStatusDaoImple();
        statusList=statusDao.getDevices(parameters);
        return  statusList;


    }


    @POST
    @Path("/adddevicestatus/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDeviceStatus(DeviceStatus deviceStatus) throws SQLException {

        String strResponse="";
        statusDao=new DeviceStatusDaoImple();
        strResponse=statusDao.addDevice(deviceStatus);
        return Response.ok(strResponse).build();


    }


    @PUT
    @Path("/updatedevicestatus/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDeviceStatus(DeviceStatus deviceStatus ,@PathParam("id") String id ) throws SQLException {

        String strResponse="";
        statusDao=new DeviceStatusDaoImple();
        strResponse=statusDao.updateDevice(deviceStatus,id);
        return Response.ok(strResponse).build();


    }
    


}