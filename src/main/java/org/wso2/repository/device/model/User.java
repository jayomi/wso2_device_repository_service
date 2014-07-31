package org.wso2.repository.device.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="user")
public class User{

    private String userId;
    private String userFname;
    private String userLname;
    private String username;
    private String passsword;
    private String email;
    private String telNo;
    private String description;
    private String role;
    
    


    public String getRole() {
		return role;
	}

    @XmlElement(name="role")
	public void setRole(String role) {
		this.role = role;
	}

	
    public String getUserId() {return userId;
    }

    @XmlElement(name="userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

   
    public String getUserFname() {
        return userFname;
    }

    @XmlElement(name="userFname")
    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    
    public String getUserLname() {
        return userLname;
    }

    @XmlElement(name="userLname")
    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    
    public String getUsername() {
        return username;
    }

    @XmlElement(name="username")
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPasssword() {
        return passsword;
    }

    @XmlElement(name="password")
    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    
    public String getEmail() {
        return email;
    }

    @XmlElement(name="email")
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getTelNo() {
        return telNo;
    }

    @XmlElement(name="telNo")
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

   
    public String getDescription() {
        return description;
    }

    @XmlElement(name="description")
    public void setDescription(String description) {
        this.description = description;
    }

}
