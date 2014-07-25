package org.wso2.repository.device.service;

import org.wso2.repository.device.data.User;

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


@Path("/user/")
public class UserService
{

    Connection connection;

    public UserService() {
        init();
    }



/*

    @DELETE
    @Path("/deletedevicetype/{id}/")
    public Response deleteDevice(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


        Statement statement = connection.createStatement();
        String strCount = "select  count(*) cnt from devmgt_isg9251.device where t_id in (select  t_id from devmgt_isg9251.device_type where t_id =" + id +")";

        ResultSet resultSet = statement.executeQuery(strCount);

        resultSet.next();

        if(resultSet.getInt("cnt") == 0)
        {
            String query = "delete from devmgt_isg9251.device_type where t_id =" +id;
            statement.execute(query);
            return Response.ok().status(200).build();

        }
        else
        {
            return Response.ok().status(405).build();

        }


    }
*/
    @GET
    @Path("/getuser/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public User getDevice(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


        Statement statement = connection.createStatement();
        String query = "select * from devmgt_isg9251.user where u_id =" +id;
        ResultSet resultSet = statement.executeQuery(query);

        User user = new User();

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

    @GET
    @Path("/getusers/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<User> getUsers() throws SQLException {

        LinkedList<User> userList = new LinkedList<User>();


        Statement statement = connection.createStatement();
        String query = "select * from devmgt_isg9251.user";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
           User user = new User();
            user.setUserId(resultSet.getString("u_id"));
            user.setUserFname(resultSet.getString("first_name"));
            user.setUserLname(resultSet.getString("last_name"));
            user.setUsername(resultSet.getString("username"));
            user.setPasssword( resultSet.getString("password"));
            user.setEmail( resultSet.getString("email"));
            user.setTelNo( resultSet.getString("tel_no"));
            user.setDescription(resultSet.getString("description"));

            userList.add(user);
        }
        return userList;

    }


    @POST
    @Path("/adduser/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUSer(User user) throws SQLException {

        Statement statement = connection.createStatement();

        String query = "insert into devmgt_isg9251.user(first_name,last_name,username,password,email,tel_no,description) values ('" +user.getUserFname()+"' , '"+user.getUserLname()+ "' , '"+user.getUsername()+"' , '"+user.getPasssword()+ "' , '"+user.getEmail()+"' , '"+user.getTelNo()+"' , '"+user.getDescription()+"')";

        statement.execute(query);
        return Response.ok().status(201).build();

    }

    /*
        @PUT
        @Path("/updateUser/{id}/")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateDevice(User user ,@PathParam("id") String id ) throws SQLException {

            Statement statement = connection.createStatement();

            String query =null;

            if( .getUserName()==null &&  user.getUserDescription()!= null)
            {
                query = "update devmgt_isg9251.device_type set t_description = '" +
                        user.getUserDescription() + "' WHERE t_id =" + id;
                statement.execul,te(query);
            }

            if( user.getUserName()!=null &&  user.getUserDescription()== null)
            {
                query = "update devmgt_isg9251.device_type set type ='"+user.getUserName() +"'  WHERE t_id =" + id;
                statement.execute(query);
            }
            if( user.getUserName()!=null &&  user.getUserDescription()!= null)
            {
                query = "update devmgt_isg9251.device_type set type ='"+user.getUserName() +"' , t_description = '" +
                        user.getUserDescription() + "' WHERE t_id =" + id;
                statement.execute(query);
            }

            return Response.ok().status(200).build();


        }

    */
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

