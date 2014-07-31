package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.DeviceType;
import org.wso2.repository.device.util.DB;

import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DeviceTypeDaoImple implements DeviceTypeDao {


    //delete device type

    public String deleteDeviceType(String id) throws Exception {

        String strResponse="";

        try{

            Connection con = DB.getConnection();
            Statement statement = con.createStatement();
            // String schema= con.getSchema();

            String strCount = "select  count(*) cnt from device where t_id in (select  t_id from device_type where t_id =" + id +")";

            ResultSet resultSet = statement.executeQuery(strCount);

            resultSet.next();

            if(resultSet.getInt("cnt") == 0)
            {
                String query = "delete from device_type where t_id =" +id;
                statement.execute(query);
                strResponse="Successfully Deleted";

            }


        }catch (SQLException e) {
            e.printStackTrace();
            strResponse="Failed.Try Again.";
            return strResponse;

        }finally {

            return strResponse;
        }


    }

    public LinkedList<DeviceType> getDeviceTypes(UriInfo parameters) {

        String strResponse="";
        LinkedList deviceTypeList=new LinkedList();

        try{

            String t_id=parameters.getQueryParameters().getFirst("typeId");
            String type=parameters.getQueryParameters().getFirst("type");
            String description=parameters.getQueryParameters().getFirst("description");
            String options=null;
            boolean firstPara = false;

            Connection con = DB.getConnection();
            Statement statement = con.createStatement();
            String query = "select * from device_type ";


            if (t_id !=null)
            {
                options = " t_id = '" + t_id +"' ";
                firstPara =true;
            }

            if (type !=null)
            {
                if (firstPara==false) {
                    options = " type = '" + type+ "' ";
                    firstPara = true;
                }else
                {
                    options = options +  "AND type = '" + type + "' ";
                }

            }
            if (description !=null)
            {
                if (firstPara==false) {
                    options = "t_description = '" + description+ "' ";
                    firstPara = true;
                }else
                {
                    options = options +  "AND t_description = '" + description + "' ";
                }

            }
            if(firstPara)
            {
                query = query + " Where " + options;
            }

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                DeviceType deviceType=new DeviceType();
                deviceType.setDeviceTypeId(resultSet.getString("t_id"));
                deviceType.setDeviceTypeName(resultSet.getString("type"));
                deviceType.setDeviceTypeDescription(resultSet.getString("t_description"));
                deviceTypeList.add(deviceType);

            }

            strResponse="Ok,Query Executed.";
            return deviceTypeList;

        }catch (Exception e) {
            e.printStackTrace();
            strResponse="Failed.Try Again.";
            System.out.println( strResponse);
            return deviceTypeList;
        }
        finally {
            return deviceTypeList;
        }

    }

    //add a new device type

    public String addDevice(DeviceType deviceType) {

        String strResponse = null;
        try {

            Connection con = DB.getConnection();
            Statement stmt = con.createStatement();
            String query = "insert into  device_type(type,t_description) values ('" + deviceType.getDeviceTypeName() + "' , '" + deviceType.getDeviceTypeDescription() + "')";

            stmt.executeUpdate(query);
            strResponse="Sucessfully Added.";
            System.out.println("OK");

        } catch (Exception ee) {
            ee.printStackTrace();
            strResponse="Failed.Try Again.";
        }finally{
            return strResponse;
        }
    }

    // update device type

    public String updateDevice(DeviceType deviceType, String id) {
        LinkedList<String> listColumns= new LinkedList<String>();
        LinkedList<String> listValues= new LinkedList<String>();
        String strResponse=null;

        try {
            Connection con = DB.getConnection();
            Statement statement = con.createStatement();

            String query =null;

            if( deviceType.getDeviceTypeId()!=null) {
                listColumns.add("t_id");
                listValues.add(deviceType.getDeviceTypeId());
            }
            if( deviceType.getDeviceTypeName()!=null) {
                listColumns.add("type");
                listValues.add(deviceType.getDeviceTypeName());
            }

            if( deviceType.getDeviceTypeDescription()!=null) {
                listColumns.add("t_description");
                listValues.add(deviceType.getDeviceTypeDescription());
            }



            for (int x= 0;x<listColumns.size();x++) {

                if(x==0)
                {
                    query = "update device_type set ";
                }

                if(x!=(listColumns.size()-1))
                {
                    query = query + listColumns.get(x) + " = '";
                    query = query + listValues.get(x) + "' , ";
                }
                else{
                    query = query + listColumns.get(x) + " = '";
                    query = query + listValues.get(x)+ "' WHERE t_id = " + id;
                }

            }
            statement.execute(query);
            strResponse="Ok,Successfully Updated";
            return strResponse;

        } catch (Exception e) {
            e.printStackTrace();
            strResponse="Failed.Try Again";
            return strResponse;
        }finally {
            return strResponse;
        }
    }
}
