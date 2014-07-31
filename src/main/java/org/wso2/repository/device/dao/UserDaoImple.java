package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.User;
import org.wso2.repository.device.util.DB;

import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class UserDaoImple implements UserDao {

    User user;

    // get user

    public LinkedList<User> getUsers(UriInfo parameters) {

        String strResponse = "";
        LinkedList userList = new LinkedList();

        try {
            String userId = parameters.getQueryParameters().getFirst("userId");
            String firstName = parameters.getQueryParameters()
                    .getFirst("fname");
            String lastName = parameters.getQueryParameters().getFirst("lname");
            String email = parameters.getQueryParameters().getFirst("email");
            String options = null;

            Connection con = DB.getConnection();
            Statement statement = con.createStatement();
            String query = "select * from user";

            boolean firstPara = false;

            if (userId != null) {
                options = " u_id = '" + userId + "' ";
                firstPara = true;
            }

            if (firstName != null) {
                if (firstPara == false) {
                    options = " first_name = '" + firstName + "' ";
                    firstPara = true;
                } else {
                    options = options + " AND first_name = '" + firstName
                            + "' ";
                }

            }

            if (lastName != null) {
                if (firstPara == false) {
                    options = " last_name = '" + lastName + "' ";
                    firstPara = true;
                } else {
                    options = options + " AND last_name = '" + lastName + "' ";
                }

            }
            if (email != null) {
                if (firstPara == false) {
                    options = " email = '" + email + "' ";
                    firstPara = true;
                } else {
                    options = options + " AND email = '" + email + "' ";
                }

            }

            if (firstPara) {
                query = query + " Where" + options;
            }

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                User user = new User();
                user.setUserId(resultSet.getString("u_id"));
                user.setUserFname(resultSet.getString("first_name"));
                user.setUserLname(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setDescription(resultSet.getString("description"));
                user.setTelNo(resultSet.getString("tel_no"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getString("role"));
                userList.add(user);

            }

            strResponse = "Ok,Query Executed";
            System.out.println(strResponse);
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
            strResponse = "Data Not Executed";
            System.out.println(strResponse);
            return userList;

        } finally {
            return userList;
        }

    }

    // delete user

    public String deleteUser(String id) {

        String strResponse = "";

        try {
            Connection connection = DB.getConnection();
            Statement statement = connection.createStatement();
            String query = "delete from user where u_id ='" + id+"'";
            statement.execute(query);
            strResponse = "Successfully Deleted";

        } catch (SQLException e) {
            e.printStackTrace();
            strResponse = "User Not Deleted";
//			return strResponse;

        } finally {

            return strResponse;

        }

    }

    // add a user
    public String addUser(User user) {

        String strResponse = null;
        try {

            String strRole="user";
            Connection con = DB.getConnection();
            Statement stmt = con.createStatement();
            String query = "insert into user (first_name,last_name,username,password,email,tel_no,description,role) values ('"
                    + user.getUserFname()
                    + "' , '"
                    + user.getUserLname()
                    + "' , '"
                    + user.getUsername()
                    + "','"
                    + user.getPasssword()
                    + "','"
                    + user.getEmail()
                    + "','"
                    + user.getTelNo() + "','" + user.getDescription() + "','"+strRole+"')";

            stmt.executeUpdate(query);
            strResponse = "Data Added";

        } catch (Exception ee) {
            ee.printStackTrace();
            strResponse = "Data Not Added";
        } finally {
            return strResponse;
        }

    }

    // update a user

    public String updateUser(User user, String id) {
        LinkedList<String> listColumns = new LinkedList<String>();
        LinkedList<String> listValues = new LinkedList<String>();
        String strResponse = null;

        try {
            Connection con = DB.getConnection();
            Statement statement = con.createStatement();

            String query = null;

            if (user.getUserId() != null) {
                listColumns.add("u_id");
                listValues.add(user.getUserId());
            }
            if (user.getUserFname() != null) {
                listColumns.add("first_name");
                listValues.add(user.getUserFname());
            }

            if (user.getUserLname() != null) {
                listColumns.add("last_name");
                listValues.add(user.getUserLname());
            }

            if (user.getUsername() != null) {
                listColumns.add("username");
                listValues.add(user.getUsername());
            }
            if (user.getPasssword() != null) {
                listColumns.add("password");
                listValues.add(user.getPasssword());
            }
            if (user.getEmail() != null) {
                listColumns.add("email");
                listValues.add(user.getEmail());
            }
            if (user.getTelNo() != null) {
                listColumns.add("tel_no");
                listValues.add(user.getTelNo());
            }
            if (user.getDescription() != null) {
                listColumns.add("description");
                listValues.add(user.getDescription());
            }

            for (int x = 0; x < listColumns.size(); x++) {

                if (x == 0) {
                    query = "update user set ";
                }

                if (x != (listColumns.size() - 1)) {
                    query = query + listColumns.get(x) + " = '";
                    query = query + listValues.get(x) + "' , ";
                } else {
                    query = query + listColumns.get(x) + " = '";
                    query = query + listValues.get(x) + "' WHERE u_id = '" + id+"'";
                }

            }
            statement.execute(query);
            strResponse = "User Updated";
            return strResponse;

        } catch (Exception e) {
            e.printStackTrace();
            strResponse = "User Not Updated";
            return strResponse;
        } finally {
            return strResponse;
        }
    }
}