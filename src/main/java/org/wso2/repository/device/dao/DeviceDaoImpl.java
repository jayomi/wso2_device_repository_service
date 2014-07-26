package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.Device;
import org.wso2.repository.device.util.DB;

import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


public class DeviceDaoImpl implements DeviceDao{

   Device device;
   Connection connection;


//delete a device

    public String deleteDevice(String id) throws Exception {

        String strResponse="";
        Connection con = DB.getConnection();
        Statement stmt = con.createStatement();
        String schema= con.getSchema();

        try{

            String strCount = "select count(*) cnt from devmgt_isg9251.transaction where d_id in (select d_id from devmgt_isg9251.device where d_id =" + id +")";

            ResultSet resultSet = stmt.executeQuery(strCount);
            resultSet.next();


            if(resultSet.getInt("cnt") == 0)
            {
                String query = "delete from devmgt_isg9251.device where d_id =" +id;
                stmt.execute(query);
                strResponse="Successfully Deleted";

            }


        }catch (SQLException e) {
            e.printStackTrace();
            strResponse="Query Executed";
            return strResponse;

        }finally {

            return strResponse;
        }

    }



/*

//get all devices

    public LinkedList<Device> getDevices() {

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
                device.setTypeId(resultSet.getString("t_id"));
                device.setStatusId(resultSet.getString("s_id"));

                deviceList.add(device);
            }

            System.out.println("ok,Data Added");
            return deviceList;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Data Not Added,Try Again.");
            return deviceList;

        }finally{
            return deviceList;
        }

    }
*/
//search method

    public LinkedList<Device> getDevices(UriInfo parameters) {

        String strResponse="";
        LinkedList deviceList=new LinkedList();

        try {
            String deviceId = parameters.getQueryParameters().getFirst("deviceId");
            String deviceName = parameters.getQueryParameters().getFirst("deviceName");
            String statusId = parameters.getQueryParameters().getFirst("statusId");
            String typeId = parameters.getQueryParameters().getFirst("typeId");
            String options = null;

            Connection con=DB.getConnection();
            Statement statement=con.createStatement();
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

            strResponse="Ok,Executed the Query";
            System.out.println( strResponse);
            return deviceList;

        }catch (Exception e) {
            e.printStackTrace();
            strResponse="Data Not Executed";
            System.out.println( strResponse);
            return deviceList;

        }finally{
            return deviceList;
        }

    }

    //add a device
    public String addDevice(Device device) {

        String strResponse = null;
        try {


            System.out.println(device.getStatusId());
            System.out.println(device.getTypeId());
            Connection con = DB.getConnection();
            Statement stmt = con.createStatement();
            String query = "insert into devmgt_isg9251.device(d_name,d_description,s_id,t_id) values ('" + device.getDeviceName() + "','" + device.getDeviceDescription() +"' , '"+device.getStatusId()+"' , '"+device.getTypeId()+"')";

            stmt.executeUpdate(query);
            strResponse="Data Added";
            System.out.println("OK");

        } catch (Exception ee) {
            ee.printStackTrace();
            strResponse="Data Not Added";
        }finally{
            return strResponse;
        }

    }

    //update a device
    public String updateDevice(Device device,String id) {

        LinkedList<String> listColumns= new LinkedList<String>();
        LinkedList<String> listValues= new LinkedList<String>();
        String strResponse=null;

        try {
            Connection con = DB.getConnection();
            Statement statement = con.createStatement();

            String query =null;

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
            strResponse="Ok,Executed the Query";
            return strResponse;

        } catch (Exception e) {
            e.printStackTrace();
            strResponse="Data Not Executed";
            return strResponse;
        }finally {
            return strResponse;
        }
    }
}
