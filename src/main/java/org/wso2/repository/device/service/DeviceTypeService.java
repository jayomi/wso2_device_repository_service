package org.wso2.repository.device.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Path;

@Path("/devicerepository/")
public class DeviceTypeService
{
    public DeviceTypeService(){
        
       /* try
        {
        DataSource dataSource = null;
    InitialContext context = new InitialContext();
    dataSource = (DataSource) context.lookup("jdbc/DeviceMgt");
    
			Connection con= dataSource.getConnection();
			Statement statement = con.createStatement();
			String query = "select * from devmgt_isg9251.device";
			ResultSet resultSet = statement.executeQuery(query);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }*/
    }
}