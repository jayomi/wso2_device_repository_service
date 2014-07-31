package org.wso2.repository.device.service;

import org.wso2.repository.device.dao.UserDao;
import org.wso2.repository.device.dao.UserDaoImple;
import org.wso2.repository.device.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.sql.SQLException;
import java.util.LinkedList;


@Path("/user/")
public class UserService
{

    UserDao userDao;
    User user;


    @DELETE
    @Path("/deleteuser/{id}/")
    public Response deleteuser(@PathParam("id") String id) throws SQLException {

        String strResponse;
        userDao=new UserDaoImple();
        strResponse=userDao.deleteUser(id);
        return Response.ok(strResponse).build();

    }




    @GET
    @Path("/getusers/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<User> getUsers(@Context UriInfo parameters) throws SQLException {
        LinkedList userList=new LinkedList();
        userDao=new UserDaoImple();
        userList=userDao.getUsers(parameters);
        return userList;

    }

    @POST
    @Path("/adduser/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adduser(User user) throws SQLException {

        String strResponse="";
        userDao=new UserDaoImple();
        strResponse=userDao.addUser(user);

        return Response.ok(strResponse).build();

    }


    @PUT
    @Path("/updateuser/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user ,@PathParam("id") String id ) throws SQLException {

        String strResponse="";
        userDao=new UserDaoImple();
        strResponse=userDao.updateUser(user, id);
        return Response.ok(strResponse).build();


    }




}

