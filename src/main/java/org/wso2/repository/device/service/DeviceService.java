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
import java.util.LinkedList;


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

    @GET
    @Path("/getall/")
    @Produces(MediaType.APPLICATION_JSON)
    public Device getAllDevice() throws SQLException {

        Statement statement = connection.createStatement();
        String query = "select * from devmgt_isg9251.device";
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

        String query = "insert into devmgt_isg9251.device(d_name,d_description,s_id,t_id) values ('" + device.getDeviceName() + "','" + device.getDeviceDescription() +"' , '"+device.getStatusId()+"' , '"+device.getTypeId()+"')";

        statement.execute(query);
        return Response.ok().status(201).build();

    }



    @PUT
    @Path("/updatedevice/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDevice(Device device ,@PathParam("id") String id ) throws SQLException {

        Statement statement = connection.createStatement();

        String query =null;


        LinkedList<String> listColumns= new LinkedList<String>();
        LinkedList<String> listValues= new LinkedList<String>();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if( device.getDeviceId()!=null) {
            listColumns.add("d_id");
            listValues.add(device.getDeviceId());
        }
        if( device.getDeviceName()!=null) {
            listColumns.add("d_name");
            listValues.add(device.getDeviceName());
        }

        if( device.getDeviceDescription()!=null) {
            listColumns.add("d_description");
            listValues.add(device.getDeviceDescription());
        }

        if( device.getStatusId()!=null) {
            listColumns.add("s_id");
            listValues.add(device.getStatusId());
        }
        if( device.getTypeId()!=null) {
            listColumns.add("t_id");
            listValues.add(device.getTypeId());
        }




        for (int x= 0;x<listColumns.size();x++) {

            if(x==0)
            {
                query = "update devmgt_isg9251.device set ";
            }

            if(x!=(listColumns.size()-1))
            {
                query = query + listColumns.get(x) + " = '";
                query = query + listValues.get(x) + "' , ";
            }
            else{
                query = query + listColumns.get(x) + " = '";
                query = query + listValues.get(x)+ "' WHERE d_id = " + id;
            }

        }
        statement.execute(query);
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