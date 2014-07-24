package org.wso2.repository.device.service;

import org.wso2.repository.device.data.DeviceStatus;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


@Path("/devicestatus/")
public class DeviceStatusService
{

    Connection connection;

    public DeviceStatusService() {
        init();
    }





    @DELETE
    @Path("/deletedevicestatus/{id}/")
    public Response deleteDeviceStatus(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


        Statement statement = connection.createStatement();
        String strCount = "select  count(*) cnt from devmgt_isg9251.device where s_id in (select  s_id from devmgt_isg9251.status where s_id =" + id +")";

        ResultSet resultSet = statement.executeQuery(strCount);

        resultSet.next();

        if(resultSet.getInt("cnt") == 0)
        {
            String query = "delete from devmgt_isg9251.status where s_id =" +id;
            statement.execute(query);
            return Response.ok().status(200).build();

        }
        else
        {
            return Response.ok().status(405).build();

        }


    }

   @GET
   @Path("/getdevicestatus/{id}/")
   @Produces(MediaType.APPLICATION_JSON)
   public DeviceStatus getDeviceStatus(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


           Statement statement = connection.createStatement();
           String query = "select * from devmgt_isg9251.status where s_id =" +id;
           ResultSet resultSet = statement.executeQuery(query);

       DeviceStatus deviceStatus = new DeviceStatus();

           while (resultSet.next()) {
               deviceStatus.setDeviceStatusId(resultSet.getString("s_id"));
               deviceStatus.setDeviceStatusName(resultSet.getString("status"));
           }
       return deviceStatus;

   }


    @GET
    @Path("/getdevicestatuses/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<DeviceStatus> getDeviceStatuses() throws SQLException {

        LinkedList<DeviceStatus> statusList = new LinkedList<DeviceStatus>();


        Statement statement = connection.createStatement();
        String query = "select * from devmgt_isg9251.status ";
        ResultSet resultSet = statement.executeQuery(query);


        while (resultSet.next()) {
            DeviceStatus deviceStatus = new DeviceStatus();

            deviceStatus.setDeviceStatusId(resultSet.getString("s_id"));
            deviceStatus.setDeviceStatusName(resultSet.getString("status"));
            statusList.add(deviceStatus);
        }
        return statusList;

    }


    @POST
    @Path("/adddevicestatus/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDeviceStatus(DeviceStatus deviceStatus) throws SQLException {

        Statement statement = connection.createStatement();

        String query = "insert into  devmgt_isg9251.status(status) values ('" + deviceStatus.getDeviceStatusName() + "')";

        statement.execute(query);
        return Response.ok().status(201).build();


    }


    @PUT
    @Path("/updatedevicestatus/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDeviceStatus(DeviceStatus deviceStatus ,@PathParam("id") String id ) throws SQLException {

        Statement statement = connection.createStatement();

        String query =null;

        if( deviceStatus.getDeviceStatusName()!=null)
        {
            query = "update devmgt_isg9251.status set status ='"+deviceStatus.getDeviceStatusName() +"' WHERE s_id =" + id;
            statement.execute(query);
        }

        return Response.ok().status(200).build();


    }
    

    final void init() {

        try {
           InitialContext context = new InitialContext();
           DataSource dataSource = (DataSource)context.lookup("jdbc/deviceRepoDS");
           connection = dataSource.getConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

}