package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.DeviceStatus;
import org.wso2.repository.device.util.DB;

import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


public class DeviceStatusDaoImple implements DeviceStatusDao {

    DeviceStatus status;

    //delete device

    public String deleteDeviceStatus(String id) throws Exception {
        String strResponse="";

        try{


            Connection connection = DB.getConnection();
            Statement statement = connection.createStatement();

            String strCount = "select  count(*) cnt from devmgt_isg9251.device where s_id in (select  s_id from devmgt_isg9251.status where s_id =" + id +")";

            ResultSet resultSet = statement.executeQuery(strCount);

            resultSet.next();

            if(resultSet.getInt("cnt") == 0)
            {
                String query = "delete from devmgt_isg9251.status where s_id =" +id;
                statement.execute(query);
                strResponse="Successfully Deleted";
            }



        }catch (SQLException e) {
            e.printStackTrace();
            strResponse="Query Not Executed";
            return strResponse;

        }finally {

            return strResponse;

        }
    }


    //get deviceStatus

    public LinkedList<DeviceStatus> getDevices(UriInfo parameters) {
        String strResponse="";
        LinkedList statusList=new LinkedList();

        try {
            String statusId = parameters.getQueryParameters().getFirst("statusId");
            String statusName = parameters.getQueryParameters().getFirst("statusName");


            String options = null;

            Connection con= DB.getConnection();
            Statement statement=con.createStatement();
            String query ="select * from devmgt_isg9251.status";

            boolean firstPara = false;

            if (statusId !=null)
            {
                options = " s_id = '" + statusId +"' ";
                firstPara =true;
            }

            if (statusName !=null)
            {
                if (firstPara==false) {
                    options = " status = '" + statusName+ "' ";
                    firstPara = true;
                }else
                {
                    options = options +  " AND status = '" + statusName + "' ";
                }

            }


            if(firstPara)
            {
                query = query + " Where " + options;
            }

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                DeviceStatus status=new DeviceStatus();
                status.setDeviceStatusId(resultSet.getString("s_id"));
                status.setDeviceStatusName(resultSet.getString("status"));
                statusList.add(status);
            }

            strResponse="Ok,Query Executed";
            System.out.println( strResponse);
            return statusList;

        }catch (Exception e) {
            e.printStackTrace();
            strResponse="Not Executed";
            System.out.println( strResponse);
            return statusList;

        }finally{
            return statusList;
        }
    }

    //add device
    public String addDevice(DeviceStatus deviceStatus) {

        String strResponse = null;
        DeviceStatus status=new DeviceStatus();
        try {

            Connection con = DB.getConnection();
            Statement stmt = con.createStatement();
            String query = "insert into devmgt_isg9251.status (s_id,status) values ('" + status.getDeviceStatusId()+ "','" + status.getDeviceStatusName() +"')";

            stmt.executeUpdate(query);
            strResponse="Data Added";


        } catch (Exception ee) {
            ee.printStackTrace();
            strResponse="Data Not Added";
        }finally{
            return strResponse;
        }

    }

    //update device
    public String updateDevice(DeviceStatus device, String id) {

        String strResponse="";

        try {
            Connection con = DB.getConnection();
            Statement statement = con.createStatement();

            String query =null;


            if( status.getDeviceStatusName()!=null) {
                query = "update devmgt_isg9251.status set status ='"+status.getDeviceStatusName() +"' WHERE s_id =" + id;
                statement.execute(query);
            }

            strResponse="Ok,Executed Query";
            return strResponse;

        } catch (Exception e) {
            e.printStackTrace();
            strResponse="Not Executed";
            return strResponse;
        }finally {
            return strResponse;
        }
    }

 }

