package org.wso2.repository.device.service;

import org.wso2.repository.device.dao.DeviceDao;
import org.wso2.repository.device.dao.DeviceDaoImpl;
import org.wso2.repository.device.model.Device;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
    @Path("/getdevice/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Device getDevice(@PathParam("id") String id) throws SQLException, NamingException {

        int intId = Integer.parseInt(id);

        Connection connection=null;
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("jdbc/deviceRepoDS");
        connection=dataSource.getConnection();
        Statement statement = connection.createStatement();
        String query = "select * from devmgt_isg9251.device where d_id =" +id;
        ResultSet resultSet = statement.executeQuery(query);

        Device device = new Device();

        while (resultSet.next()) {

            device.setDeviceId(resultSet.getString("d_id"));
            device.setDeviceName(resultSet.getString("d_name"));
            device.setDeviceDescription(resultSet.getString("d_description"));
            device.setStatusId(resultSet.getString("s_id"));
        }
        return device;

    }


    @GET
    @Path("/getdevices/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevices() throws SQLException {

        
        deviceDao=new DeviceDaoImpl();
        deviceDao.getDevices();
        return Response.ok().status(200).build();

    }



    @GET
    @Path("/searchdevice/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDevice(@Context UriInfo parameters) throws SQLException {
        deviceDao=new DeviceDaoImpl();
        deviceDao.searchDevice(parameters);
        return Response.ok().status(200).build();

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