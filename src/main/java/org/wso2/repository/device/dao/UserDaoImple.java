package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.User;
import org.wso2.repository.device.util.DB;

import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * Created by jayomi on 7/26/14.
 */
public class UserDaoImple implements UserDao {

    User user;

    public LinkedList<User> getUsers(UriInfo parameters) {

        String strResponse="";
        LinkedList userList=new LinkedList();

        try {
            String userId = parameters.getQueryParameters().getFirst("userId");
            String firstName = parameters.getQueryParameters().getFirst("fname");
            String lastName = parameters.getQueryParameters().getFirst("lname");
            String email = parameters.getQueryParameters().getFirst("email");
            String options = null;

            Connection con=DB.getConnection();
            Statement statement=con.createStatement();
            String query ="select * from devmgt_isg9251.user";

            boolean firstPara = false;

            if (userId !=null)
            {
                options = " u_id = '" + userId +"' ";
                firstPara =true;
            }

            if (firstName !=null)
            {
                if (firstPara==false) {
                    options = " first_name = '" + firstName+ "' ";
                    firstPara = true;
                }else
                {
                    options = options +  " AND first_name = '" + firstName + "' ";
                }

            }

            if (lastName !=null)
            {
                if (firstPara==false) {
                    options = " last_name = '" + lastName + "' ";
                    firstPara = true;
                }else
                {
                    options = options +  " AND last_name = '" + lastName + "' ";
                }

            }
            if (email !=null)
            {
                if (firstPara==false) {
                    options = " email = '" + email + "' ";
                    firstPara = true;
                }else
                {
                    options = options +  " AND email = '" + email + "' ";
                }

            }

            if(firstPara)
            {
                query = query + " Where" + options;
            }

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                User user=new User();
                user.setUserId(resultSet.getString("u_id"));
                user.setUserFname(resultSet.getString("first_name"));
                user.setUserLname(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                userList.add(user);


            }

            strResponse="Ok,Executed the Query";
            System.out.println( strResponse);
            return userList;

        }catch (Exception e) {
            e.printStackTrace();
            strResponse="Data Not Executed";
            System.out.println( strResponse);
            return userList;

        }finally{
            return userList;
        }

    }



    public String deleteUser(String id) {
        return null;
    }

    public String addUser(User user) {
        String strResponse = null;
        try {



            Connection con = DB.getConnection();
            Statement stmt = con.createStatement();
            String query = "insert into devmgt_isg9251.user(u_id,first_name,last_name,username,password,email,tel_no,description) values ('" + user.getUserId()+ "','" + user.getUserFname() +"' , '"+user.getUserLname()+"' , '"+user.getUsername()+"','"+user.getPasssword()+"','"+user.getEmail()+"','"+user.getTelNo()+"','"+user.getDescription()+"')";

            stmt.executeUpdate(query);
            strResponse="Data Added";


        } catch (Exception ee) {
            ee.printStackTrace();
            strResponse="Data Not Added";
        }finally{
            return strResponse;
        }

    }

    public String updateUser(User user, String id) {
        return null;
    }
}
