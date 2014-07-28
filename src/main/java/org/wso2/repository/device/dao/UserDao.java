package org.wso2.repository.device.dao;


import org.wso2.repository.device.model.User;
import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;

public interface UserDao {

    public LinkedList<User> getUsers(UriInfo parameters);
    public String deleteUser(String id);
    public String addUser(User user);
    public String updateUser(User user,String id);


}