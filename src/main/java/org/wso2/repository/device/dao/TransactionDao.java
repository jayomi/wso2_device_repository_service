package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.Transaction;

import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;


public interface TransactionDao {


    public String deleteTransaction(String id);
    public Transaction getTransaction(String id) throws Exception;
    public LinkedList<Transaction> getTransactions(UriInfo parameters);
    public LinkedList<Transaction> getTransactionsDetail(UriInfo parameters);
    public String addTransaction(Transaction transaction);
    public String updateTransaction(Transaction transaction, String id);
}
