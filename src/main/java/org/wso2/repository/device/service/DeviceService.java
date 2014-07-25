package org.wso2.repository.device.service;

import org.wso2.repository.device.dao.DeviceDao;
import org.wso2.repository.device.dao.DeviceDaoImpl;
import org.wso2.repository.device.model.Device;
import org.wso2.repository.device.util.DB;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


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
   public Device getDevice(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);

       try {
           Connection con = DB.getConnection();

            Statement statement = con.createStatement();
           String query = "select * from devmgt_isg9251.device where d_id =" +id;
           ResultSet resultSet = statement.executeQuery(query);

//           Device device = new Device();

           device=new Device();
           while (resultSet.next()) {

               device.setDeviceId(resultSet.getString("d_id"));
               device.setDeviceName(resultSet.getString("d_name"));
               device.setDeviceDescription(resultSet.getString("d_description"));
               device.setStatusId(resultSet.getString("s_id"));
               device.setStatusId(resultSet.getString("t_id"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return device;

   }

    @GET
    @Path("/getdevices/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<Device> getDevices() throws SQLException {

        LinkedList<Device> deviceList = new LinkedList<Device>();


        Connection con = null;
        try {
            con = DB.getConnection();


        Statement statement = con.createStatement();
        String query = "select * from devmgt_isg9251.device ";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Device device=new Device();
            device.setDeviceId(resultSet.getString("d_id"));
            device.setDeviceName(resultSet.getString("d_name"));
            device.setDeviceDescription(resultSet.getString("d_description"));
            device.setStatusId(resultSet.getString("s_id"));

            deviceList.add(device);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;

    }

    /*
    public ArrayList<HashMap<String,Object>> getAll(String query) throws SQLException {

            try {

                //create statement
                Statement statement = connection.createStatement();

               query= "select * from devmgt_isg9251.device";

                //query
                ResultSet Result = null;
                boolean Returning_Rows = statement.execute(query);
                if (Returning_Rows)
                     Result = statement.getResultSet();
                else
                    return new ArrayList<HashMap<String,Object>>();

                //get metadata
                ResultSetMetaData Meta =Result.getMetaData();


                //get column names
                int Col_Count = Meta.getColumnCount();
                ArrayList<String> Cols = new ArrayList<String>();
                for (int Index=1; Index<=Col_Count; Index++)
                    Cols.add(Meta.getColumnName(Index));

                //fetch out rows
                ArrayList<HashMap<String,Object>> Rows =
                        new ArrayList<HashMap<String,Object>>();

                while (Result.next()) {
                    HashMap<String,Object> Row = new HashMap<String,Object>();
                    for (String Col_Name:Cols) {
                        Object Val = Result.getObject(Col_Name);
                        Row.put(Col_Name,Val);
                    }
                    Rows.add(Row);
                }
                //close statement
                statement.close();

                //pass back rows
                return Rows;
            }
            catch (Exception Ex) {
                System.out.print(Ex.getMessage());
                return new ArrayList<HashMap<String,Object>>();
            }
     }
*/

    @GET
    @Path("/searchdevice/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<Device> searchDevice(@Context UriInfo parameters) throws SQLException {


        String deviceId = parameters.getQueryParameters().getFirst("deviceId");
        String deviceName = parameters.getQueryParameters().getFirst("deviceName");
        String statusId = parameters.getQueryParameters().getFirst("statusId");
        String typeId = parameters.getQueryParameters().getFirst("typeId");
        String options = null;

        String query ="select * from devmgt_isg9251.device";

        boolean firstPara = false;

        if (deviceId !=null)
        {
            options = " d_id = '" + deviceId +"' ";
            firstPara =true;
        }

        if (deviceName !=null)
        {
            if (firstPara==false) {
                options = " d_name = '" + deviceName+ "' ";
                firstPara = true;
            }else
            {
                options = options +  " AND d_name = '" + deviceName + "' ";
            }

        }

        if (statusId !=null)
        {
            if (firstPara==false) {
                options = " s_id = '" + statusId + "' ";
                firstPara = true;
            }else
            {
                options = options +  " AND s_id = '" + statusId + "' ";
            }

        }
        if (typeId !=null)
        {
            if (firstPara==false) {
                options = " t_id = '" + typeId + "' ";
                firstPara = true;
            }else
            {
                options = options +  " AND t_id = '" + typeId + "' ";
            }

        }
        if(firstPara)
        {
            query = query + " Where " + options;
        }


        LinkedList deviceList=new LinkedList();
        //Device device=new Device();
        Connection con = null;
        try {
            con = DB.getConnection();


        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);


       while (resultSet.next()) {

            Device device=new Device();
            device.setDeviceId(resultSet.getString("d_id"));
            device.setDeviceName(resultSet.getString("d_name"));
            device.setDeviceDescription(resultSet.getString("d_description"));
            device.setStatusId(resultSet.getString("s_id"));
            device.setTypeId(resultSet.getString("t_id"));
            deviceList.add(device);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        Connection con= null;
        try {
            con = DB.getConnection();

        Statement statement = con.createStatement();

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

        } catch (Exception e) {
            e.printStackTrace();
        }
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