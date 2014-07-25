package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.Device;
import org.wso2.repository.device.util.DB;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


/**
 * Created by jayomi on 7/25/14.
 */
public class DeviceDaoImpl implements DeviceDao{

   Device device;
   Connection connection;


//
//    public void init() {
//        try {
//            InitialContext context = new InitialContext();
//            DataSource dataSource = (DataSource)context.lookup("jdbc/deviceRepoDS");
//            connection = dataSource.getConnection();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    public String deleteDevice(String id) {

        String strResponse="";

        try{

            Statement statement = connection.createStatement();
            String strCount = "select  count(*) cnt from devmgt_isg9251.transaction where d_id in (select d_id from devmgt_isg9251.device where d_id =" + id +")";

            ResultSet resultSet = statement.executeQuery(strCount);

            resultSet.next();


            if(resultSet.getInt("cnt") == 0)
            {
                String query = "delete from devmgt_isg9251.device where d_id =" +id;
                statement.execute(query);
                strResponse="Data Add";
                System.out.println("ok");

            }


        }catch (SQLException e) {
            e.printStackTrace();

        }finally {
            strResponse="Data Not Added";
            return strResponse;
        }

    }

    public Device getDevice() {
        return null;
    }

    public LinkedList<Device> getAllDevice() {

        LinkedList<Device> deviceList = new LinkedList<Device>();
        String strResponse=null;

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
        }finally {

        }
        return deviceList;

    }

    public LinkedList<Device> searchDevice() {
        return null;
    }

    public String addDevice(Device device) {

        String strResponse = null;
        try {


            System.out.println(device.getStatusId());
            System.out.println(device.getTypeId());
            Connection con = DB.getConnection();
            Statement stmt = con.createStatement();
//            String query = "INSERT INTO devmgt_isg9251.device(d_name,d_description,s_id,t_id) VALUES('" + device.getDeviceName()
//                    + "','" + device.getDeviceDescription() + "','"
//                    + device.getStatusId() + "','" + device.getTypeId() + "')";

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

    public Response updateDevice(Device device) {
        return null;
    }
}
