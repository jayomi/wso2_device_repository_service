package org.wso2.repository.device.service;

import demo.jaxrs.server.Customer;
import demo.jaxrs.server.Order;

import java.sql.Connection;
import java.sql.ResultSet;
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
import javax.ws.rs.core.Response;


@Path("/devicerepository/")
public class DeviceTypeService
{
    long currentId = 123;
    Map<Long, Customer> customers = new HashMap<Long, Customer>();
    Map<Long, Order> orders = new HashMap<Long, Order>();

    public DeviceTypeService() {
        init();
    }

        
   @GET
   @Path("/getdevicetype/{id}/")
   public String getDevice(@PathParam("id") String id) {
       
        int intId = Integer.parseInt(id);
        
       try {
           InitialContext context = new InitialContext();
           DataSource dataSource = (DataSource)context.lookup("jdbc/deviceRepoDS");
           Connection con = dataSource.getConnection();
           Statement statement = con.createStatement();
           String query = "select * from devmgt_isg9251.device";
           ResultSet rs = statement.executeQuery(query);
           
           
           while (rs.next()) {


s=rs.getString("d_name");

}
           
           //System.out.println(s);
           
       } catch (Exception e) {
           // TODO: handle exception
           ex=e;
           s="error";
       }finally{
            return s+"\n"+ex;
       }
     
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
           Connection con = dataSource.getConnection();
        }
        catch
        
        
    }

}