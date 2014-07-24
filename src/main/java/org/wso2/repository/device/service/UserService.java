package org.wso2.repository.device.service;

import org.wso2.repository.device.data.User;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jayomi on 7/24/14.
 */
public class UserService {

    Connection connection;

    public UserService(Connection connection) {
       init();
    }

    @Path("/getuser/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") String id) throws SQLException {

        java.sql.Statement statement= connection.createStatement();
        String query="select * from devmgt_isg9251.user where u_id= "+id;

        User user=new User();

        ResultSet resultSet;
        resultSet = statement.executeQuery(query);


        while (resultSet.next()) {

            user.setUserId(resultSet.getString("u_id"));
            user.setUserFname(resultSet.getString("first_name"));
            user.setUserLname(resultSet.getString("last_name"));
            user.setUsername(resultSet.getString("username"));
            user.setPasssword( resultSet.getString("password"));
            user.setEmail( resultSet.getString("email"));
            user.setTelNo( resultSet.getString("tel_no"));
            user.setDescription(resultSet.getString("description"));

        }

        return user;
    }




    final void init(){
       try{

           InitialContext context=new InitialContext();
           DataSource dataSource = (DataSource) context.lookup("jdbc/deviceRepoDS");
           connection=dataSource.getConnection();

       }catch(Exception e){
           e.printStackTrace();
       }
    }

}
