package org.wso2.repository.device.service;

import org.wso2.repository.device.dao.DeviceDao;
import org.wso2.repository.device.dao.DeviceDaoImpl;
import org.wso2.repository.device.model.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;


@Path("/device/")
public class DeviceService
{

    DeviceDao deviceDao;
    Device device;

    @DELETE
    @Path("/deletedevice/{id}/")
    public Response deleteDevice(@PathParam("id") String id) throws SQLException {

      String strResponse;
      deviceDao=new DeviceDaoImpl();

       strResponse=deviceDao.deleteDevice(id);
       return Response.ok(strResponse).build();


    }


    @GET
    @Path("/getdevices/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevices() throws SQLException {

        String strResponse="";
        deviceDao=new DeviceDaoImpl();
        strResponse=deviceDao.getDevices();
        return Response.ok(strResponse).build();

    }


    @GET
    @Path("/searchdevice/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDevice(UriInfo parameters) throws SQLException {

        deviceDao=new DeviceDaoImpl();
        String strResponse=deviceDao.searchDevice(parameters);
        return Response.ok(strResponse).build();

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

        Connection con= null;

        return Response.ok().status(200).build();


    }



    final void init() {

        try {
//           InitialContext context = new InitialContext();
//           DataSource dataSource = (DataSource)context.lookup("jdbc/deviceRepoDS");
//           connection = dataSource.getConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }


}