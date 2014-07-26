package org.wso2.repository.user.service;

import org.wso2.repository.device.dao.UserDao;
import org.wso2.repository.device.dao.UserDaoImple;
import org.wso2.repository.device.model.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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


@Path("/user/")
public class UserService
{

    UserDao userDao;
    User user;

/*
    @DELETE
    @Path("/deleteuser/{id}/")
    public Response deleteuser(@PathParam("id") String id) throws SQLException {

        String strResponse;
        UserDao=new UserDaoImple();
        strResponse=userDao.deleteUser(id);
        return Response.ok(strResponse).build();

    }

   */


    @GET
    @Path("/getusers/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<User> getUsers(@Context UriInfo parameters) throws SQLException {
        LinkedList userList=new LinkedList();
        userDao=new UserDaoImple();
        userList=userDao.getUsers(parameters);
        return userList;

    }
/*
    @POST
    @Path("/adduser/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adduser(User user) throws SQLException {

        String strResponse="";
        userDao=new UserDaoImple();
        strResponse=userDao.adduser(user);

        return Response.ok(strResponse).build();

    }


    @PUT
    @Path("/updateuser/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateuser(User user ,@PathParam("id") String id ) throws SQLException {

        String strResponse="";
        userDao=new UserDaoImple();
        strResponse=userDao.updateuser(user, id);
        return Response.ok(strResponse).build();


    }

*/


}

