package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.TransactionStatus;

import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;

public interface TransactionStatusDao {
    public String deleteTransactionStatus(String id) throws Exception;
    public LinkedList<TransactionStatus> getTransactionStatus(UriInfo parameters);
    public String addTransactionStatus(TransactionStatus transactionStatus);
    public String updateTransactionStatus(TransactionStatus tstatus,String id);


}
