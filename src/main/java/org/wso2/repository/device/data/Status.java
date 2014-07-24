package org.wso2.repository.device.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jayomi on 7/24/14.
 */

@XmlRootElement(name="Status")
public class Status {

    private String statusId;
    private String status;


    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
