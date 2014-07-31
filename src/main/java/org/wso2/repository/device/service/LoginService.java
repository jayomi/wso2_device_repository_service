package org.wso2.repository.device.service;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.wso2.repository.device.dao.LoginImpl;
import org.wso2.repository.device.dao.TransactionDaoImpl;
import org.wso2.repository.device.model.Transaction;
import org.wso2.repository.device.model.User;


@Path("/login/")
public class LoginService {
	
	LoginImpl loginImpl;
	
	   @GET
	   @Path("/getlogin/")
	   @Produces(MediaType.APPLICATION_JSON)
	   public LinkedList<User> getLogin(@Context UriInfo parameters) {
		   
		   loginImpl=new LoginImpl();
		   User user=loginImpl.getLogin(parameters);
		   LinkedList userList=new LinkedList();
		   userList.add(user);
		   
		   return userList;

	   }

}
