package org.wso2.repository.device.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name="Transaction")
public class Transaction
{
    String transactionId;
    String deviceId;
    String userId;
    String transactionStatusId;
    Date transactionDate;
    Date returnDate;
    Date dueDate;

    public String getTransactionId() {
        return transactionId;
    }

    @XmlElement(name="transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    @XmlElement(name="deviceId")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    @XmlElement(name="userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionStatusId() {
        return transactionStatusId;
    }

    @XmlElement(name="transactionStatusId")
    public void setTransactionStatusId(String transactionStatusId) {
        this.transactionStatusId = transactionStatusId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    @XmlElement(name="transactionDate")
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    @XmlElement(name="returnDate")
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    @XmlElement(name="dueDate")
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}