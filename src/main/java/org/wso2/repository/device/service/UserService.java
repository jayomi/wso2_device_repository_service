package org.wso2.repository.device.service;

import org.wso2.repository.device.dao.UserDao;
import org.wso2.repository.device.dao.UserDaoImple;
import org.wso2.repository.device.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.LinkedList;


@Path("/user/")
public class UserService
{

    UserDao userDao;
    User user;

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

    //get all users
    @GET
    @Path("/getusers/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<User> getUsers() {


        LinkedList userList=new LinkedList();
        userDao=new UserDaoImple();
        userList=userDao.getUsers();
        return userList;

    }

    @GET
    @Path("/searchuser/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<User> searchuser(@Context UriInfo parameters) throws SQLException {
        LinkedList userList=new LinkedList();
        userDao=new UserDaoImple();
        userList=userDao.searchUser(parameters);
        return userList;

    }


/*

    @POST
    @Path("/adduser/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUSer(User user) throws SQLException {

        Statement statement = connection.createStatement();

        String query = "insert into devmgt_isg9251.user(first_name,last_name,username,password,email,tel_no,description) values ('" +user.getUserFname(resultSet.getString("first_name"))+"' , '"+user.getUserLname()+ "' , '"+user.getUsername()+"' , '"+user.getPasssword()+ "' , '"+user.getEmail()+"' , '"+user.getTelNo()+"' , '"+user.getDescription()+"')";

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


}

