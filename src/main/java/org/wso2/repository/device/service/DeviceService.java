package org.wso2.repository.device.service;

import org.wso2.repository.device.data.Device;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Path("/device/")
public class DeviceService
{

    Connection connection;

    public DeviceService() {
        init();
    }





    @DELETE
    @Path("/deletedevice/{id}/")
    public Response deleteDevice(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


        Statement statement = connection.createStatement();
        String strCount = "select  count(*) cnt from devmgt_isg9251.transaction where d_id in (select d_id from devmgt_isg9251.device where d_id =" + id +")";

        ResultSet resultSet = statement.executeQuery(strCount);

        resultSet.next();

        if(resultSet.getInt("cnt") == 0)
        {
            String query = "delete from devmgt_isg9251.device where d_id =" +id;
            statement.execute(query);
            return Response.ok().status(200).build();

        }
        else
        {
            return Response.ok().status(405).build();

        }
    }


   @GET
   @Path("/getdevice/{id}/")
   @Produces(MediaType.APPLICATION_JSON)
   public Device getDevice(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


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

    @POST
    @Path("/adddevice/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDevice(Device device) throws SQLException {

        Statement statement = connection.createStatement();

        String query = "insert into  devmgt_isg9251.device(d_name,d_description,s_id,t_id) values ('" + device.getDeviceName() + "' , '" + device.getDeviceDescription() +","+device.getStatusId()+","+device.getTypeId()+")";

        statement.execute(query);
        return Response.ok().status(201).build();


    }

/*
    @PUT
    @Path("/updatedevicetype/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDevice(DeviceType deviceType ,@PathParam("id") String id ) throws SQLException {

        Statement statement = connection.createStatement();

        String query =null;

        if( deviceType.getDeviceTypeName()==null &&  deviceType.getDeviceTypeDescription()!= null)
        {
            query = "update devmgt_isg9251.device_type set t_description = '" +
                    deviceType.getDeviceTypeDescription() + "' WHERE t_id =" + id;
            statement.execute(query);
        }

        if( deviceType.getDeviceTypeName()!=null &&  deviceType.getDeviceTypeDescription()== null)
        {
            query = "update devmgt_isg9251.device_type set type ='"+deviceType.getDeviceTypeName() +"'  WHERE t_id =" + id;
            statement.execute(query);
        }
        if( deviceType.getDeviceTypeName()!=null &&  deviceType.getDeviceTypeDescription()!= null)
        {
            query = "update devmgt_isg9251.device_type set type ='"+deviceType.getDeviceTypeName() +"' , t_description = '" +
                    deviceType.getDeviceTypeDescription() + "' WHERE t_id =" + id;
            statement.execute(query);
        }

        return Response.ok().status(200).build();


    }
     */

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