package org.wso2.repository.device.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.core.UriInfo;

import org.wso2.repository.device.model.User;
import org.wso2.repository.device.util.DB;

public class LoginImpl {

	User user=null;

	public User getLogin(UriInfo parameters) {

		String userName = parameters.getQueryParameters().getFirst("username");
		String passWord = parameters.getQueryParameters().getFirst("password");
		String strResponse = "";
		String salt = "";
		
		System.out.println(userName+"   "+passWord);
		try {

			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String query = "select salt from devmgt_isg9251.user where username='"
					+ userName + "'";
			ResultSet resultSet = statement.executeQuery(query);

			if (resultSet.next()) {
				salt = resultSet.getString("salt");
				System.out.println(salt);
				String strConcatPassWord = passWord + salt;

				String query2 = "select * from devmgt_isg9251.user where password='"
						+ strConcatPassWord + "'";
				ResultSet resultSet2 = statement.executeQuery(query2);

				if (resultSet2.next()) {

					user = new User();
					user.setUserId(resultSet2.getString("u_id"));
					user.setUserFname(resultSet2.getString("first_name"));
					user.setUserLname(resultSet2.getString("last_name"));
					user.setEmail(resultSet2.getString("email"));
					user.setDescription(resultSet2.getString("description"));
					user.setTelNo(resultSet2.getString("tel_no"));
					user.setUsername(resultSet2.getString("username"));
					user.setRole(resultSet2.getString("role"));
					
					System.out.println(resultSet2.getString("first_name"));

					return user;

				}

			} else {
				return user;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

}
