package org.wso2.repository.device.service;


import org.wso2.repository.device.dao.DeviceDaoImpl;
import org.wso2.repository.device.dao.TransactionDao;
import org.wso2.repository.device.dao.TransactionDaoImpl;
import org.wso2.repository.device.model.Transaction;
import org.wso2.repository.device.model.TransactionStatus;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
/*           InitialContext context = new InitialContext();
           DataSource dataSource = (DataSource)context.lookup("jdbc/deviceRepoDS");
           connection = dataSource.getConnection();*/

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

}