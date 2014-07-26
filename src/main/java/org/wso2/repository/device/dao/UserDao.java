package org.wso2.repository.device.dao;


import org.wso2.repository.device.model.User;
import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;

public interface UserDao {


    public User getUser();
    public LinkedList<User> getUsers();
    public LinkedList<User> searchUser(UriInfo parameters);
    public String removeUser(String id);
    public String addUser(User user);
    public String updateUser(User user,String id);


}
