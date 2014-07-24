package org.wso2.repository.device.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jayomi on 7/24/14.
 */

@XmlRootElement(name="User")
public class User{

    private String userId;
    private String userFname;
    private String userLname;
    private String username;
    private String passsword;
    private String email;
    private String telNo;
    private String description;


    @XmlElement(name="userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @XmlElement(name="userFname")
    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }
    @XmlElement(name="userLname")
    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }
    @XmlElement(name="username")
    public String getUsername() {
        return username;
    }
    @XmlElement(name="passsword")
    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @XmlElement(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement(name="telNo")
    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
    @XmlElement(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
