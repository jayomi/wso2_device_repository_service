package org.wso2.repository.device.service;


import org.wso2.repository.device.dao.TransactionDao;
import org.wso2.repository.device.dao.TransactionDaoImpl;
import org.wso2.repository.device.model.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

import java.util.LinkedList;


@Path("/transaction/")
public class TransactionService
{

    TransactionDao transactionDao;


    public TransactionService() {
        init();
    }



    @DELETE
    @Path("/deletetransaction/{id}/")
    public Response deleteTransaction(@PathParam("id") String id) throws SQLException {

        String strResponse;
        transactionDao=new TransactionDaoImpl();
        strResponse=transactionDao.deleteTransaction(id);
        return Response.ok(strResponse).build();

    }

   @GET
   @Path("/gettransaction/{id}/")
   @Produces(MediaType.APPLICATION_JSON)
   public Transaction getTransaction(@PathParam("id") String id) {

       Transaction transaction =new Transaction();
       transactionDao=new TransactionDaoImpl();
       try {
           transaction= transactionDao.getTransaction(id);
       } catch (Exception e) {

       }
       return transaction;

   }


    @GET
    @Path("/gettransactions/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<Transaction> getTransactions(@Context UriInfo parameters) throws SQLException {

        LinkedList<Transaction> transactionsList =new LinkedList<Transaction>();
        transactionDao=new TransactionDaoImpl();
        transactionsList=transactionDao.getTransactions(parameters);
        return transactionsList;


    }

    @GET
    @Path("/gettransactionsdetail/")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<Transaction> getTransactionsDetail(@Context UriInfo parameters) throws SQLException {

        LinkedList<Transaction> transactionsList = new LinkedList<Transaction>();
        transactionDao=new TransactionDaoImpl();
        transactionsList=transactionDao.getTransactionsDetail(parameters);
        return transactionsList;
    }

    @POST
    @Path("/addtransaction/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTransaction(Transaction transaction) throws SQLException {


        String strResponse="";
        transactionDao=new TransactionDaoImpl();
        strResponse=transactionDao.addTransaction(transaction);
        return Response.ok(strResponse).build();
    }


    @PUT
    @Path("/updatetransaction/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTransaction(Transaction transaction ,@PathParam("id") String id ) throws SQLException {

        String strResponse="";
        transactionDao=new TransactionDaoImpl();
        strResponse=transactionDao.updateTransaction(transaction,id);
        return Response.ok(strResponse).build();


    }
    

    final void init() {

        try {

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

}