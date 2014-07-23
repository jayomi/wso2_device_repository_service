package org.wso2.repository.device.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TransactionStatus")
public class TransactionStatus
{
    
    String transactionStatusId;
    String transactionStatusName;

    public String getTransactionStatusId() {
        return transactionStatusId;
    }

    @XmlElement(name="transactionStatusId")
    public void setTransactionStatusId(String transactionStatusId) {
        this.transactionStatusId = transactionStatusId;
    }

    public String getTransactionStatusName() {
        return transactionStatusName;
    }

    @XmlElement(name="transactionStatusName")
    public void setTransactionStatusName(String transactionStatusName) {
        this.transactionStatusName = transactionStatusName;
    }
}