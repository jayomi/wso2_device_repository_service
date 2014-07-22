package org.wso2.repository.device.service;

import demo.jaxrs.server.Customer;
import demo.jaxrs.server.Order;
import org.json.JSONObject;
import org.wso2.repository.device.data.DeviceType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Path;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/devicerepository/")
public class DeviceTypeService
{
    long currentId = 123;
    Map<Long, Customer> customers = new HashMap<Long, Customer>();
    Map<Long, Order> orders = new HashMap<Long, Order>();
    Connection connection;

    public DeviceTypeService() {
        init();
    }

        
   @GET
   @Path("/getdevicetype/{id}/")
   @Produces(MediaType.APPLICATION_JSON)
   public DeviceType getDevice(@PathParam("id") String id) throws SQLException {
       
        int intId = Integer.parseInt(id);
        

           Statement statement = connection.createStatement();
           String query = "select * from devmgt_isg9251.device_type where t_id =" +id;
           ResultSet resultSet = statement.executeQuery(query);

       DeviceType deviceType = new DeviceType();

           while (resultSet.next()) {
               deviceType.setDeviceTypeId(resultSet.getString("t_id"));
               deviceType.setDeviceTypeName(resultSet.getString("type"));
               deviceType.setDeviceTypeDescription(resultSet.getString("t_description"));
           }
       return deviceType;

   }
   
    @GET
    @Path("/getdevicetypetest/")
    @Produces("text/xml")
    public DeviceType getDevices() {



        DeviceType deviceType = new DeviceType();


        deviceType.setDeviceTypeId("t_id");
        deviceType.setDeviceTypeName("type");
        deviceType.setDeviceTypeDescription("t_description");

        return deviceType;

    }

    @GET
    @Path("/getdevicetypetestnew/")
    @Produces("text/xml")
    public Response getDevicess() {

        DeviceType deviceType = new DeviceType();


        deviceType.setDeviceTypeId("t_id");
        deviceType.setDeviceTypeName("type");
        deviceType.setDeviceTypeDescription("t_description");

        return Response.ok(deviceType).build();

    }

    

    final void init() {
        Customer c = new Customer();
        c.setName("John");
        c.setId(123);
        customers.put(c.getId(), c);

        Order o = new Order();
        o.setDescription("order 223");
        o.setId(223);
        orders.put(o.getId(), o);
        
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