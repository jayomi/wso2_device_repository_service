package org.wso2.repository.device.service;

import org.wso2.repository.device.data.User;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Path("/devicetype/")
public class UserService
{

    Connection connection;

    public UserService() {
        init();
    }



/*

    @DELETE
    @Path("/deletedevicetype/{id}/")
    public Response deleteDevice(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


        Statement statement = connection.createStatement();
        String strCount = "select  count(*) cnt from devmgt_isg9251.device where t_id in (select  t_id from devmgt_isg9251.device_type where t_id =" + id +")";

        ResultSet resultSet = statement.executeQuery(strCount);

        resultSet.next();

        if(resultSet.getInt("cnt") == 0)
        {
            String query = "delete from devmgt_isg9251.device_type where t_id =" +id;
            statement.execute(query);
            return Response.ok().status(200).build();

        }
        else
        {
            return Response.ok().status(405).build();

        }


    }
*/
    @GET
    @Path("/getuser/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public User getDevice(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


        Statement statement = connection.createStatement();
        String query = "select * from devmgt_isg9251.user where u_id =" +id;
        ResultSet resultSet = statement.executeQuery(query);

        User user = new User();

        while (resultSet.next()) {
            user.setUserId(resultSet.getString("u_id"));

        }
        return user;

    }
/*
    @GET
    @Path("/getdevicetypes/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<DeviceType> getDevices() throws SQLException {

        LinkedList<DeviceType> deviceTypeList = new LinkedList<DeviceType>();


        Statement statement = connection.createStatement();
        String query = "select * from devmgt_isg9251.device_type ";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            DeviceType deviceType = new DeviceType();
            deviceType.setDeviceTypeId(resultSet.getString("t_id"));
            deviceType.setDeviceTypeName(resultSet.getString("type"));
            deviceType.setDeviceTypeDescription(resultSet.getString("t_description"));
            deviceTypeList.add(deviceType);
        }
        return deviceTypeList;

    }


    @POST
    @Path("/adddevicetype/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDevice(DeviceType deviceType) throws SQLException {

        Statement statement = connection.createStatement();

        String query = "insert into  devmgt_isg9251.device_type(type,t_description) values ('" + deviceType.getDeviceTypeName() + "' , '" + deviceType.getDeviceTypeDescription() + "')";

        statement.execute(query);
        return Response.ok().status(201).build();


    }


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

