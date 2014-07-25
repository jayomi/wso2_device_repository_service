package org.wso2.repository.device.dao;

import org.wso2.repository.device.data.Device;
import org.wso2.repository.device.util.DB;

import javax.naming.InitialContext;
import javax.sql.DataSource;
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

    public DeviceDaoImpl() {
      init();
    }





    public void init() {
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

    public Response deleteDevice(String id) {

        try{

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

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Device getDevice() {
        return null;
    }

    public LinkedList<Device> getAllDevice() {
        return null;
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
            String query = "INSERT INTO devmgt_isg9251.device(d_name,d_description,s_id,t_id) VALUES('" + device.getDeviceName()
                    + "','" + device.getDeviceDescription() + "','"
                    + device.getStatusId() + "','" + device.getTypeId() + "')";

            stmt.executeUpdate(query);
            strResponse="Data Added";
            System.out.println("OK");

        } catch (Exception e) {
            e.printStackTrace();
            strResponse="Data Not Added";
        }finally{
            return strResponse;
        }
    }

    public Response updateDevice(Device device) {
        return null;
    }
}
