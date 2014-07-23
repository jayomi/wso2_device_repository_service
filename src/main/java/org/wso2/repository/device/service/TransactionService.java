package org.wso2.repository.device.service;


import org.wso2.repository.device.data.Transaction;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    Connection connection;

    public TransactionService() {
        init();
    }



    @DELETE
    @Path("/deletetransaction/{id}/")
    public Response deleteTransaction(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


        Statement statement = connection.createStatement();
        String query = "delete from devmgt_isg9251.transaction where t_id =" +id;
        statement.execute(query);
        return Response.ok().status(200).build();

    }

   @GET
   @Path("/gettransaction/{id}/")
   @Produces(MediaType.APPLICATION_JSON)
   public Transaction getTransaction(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


           Statement statement = connection.createStatement();
           String query = "select * from devmgt_isg9251.transaction where t_id =" +id;
           ResultSet resultSet = statement.executeQuery(query);

       Transaction transaction = new Transaction();

           while (resultSet.next()) {
               transaction.setTransactionId(resultSet.getString("t_id"));
               transaction.setDeviceId(resultSet.getString("d_id"));
               transaction.setUserId(resultSet.getString("u_id"));
               transaction.setTransactionStatusId(resultSet.getString("ts_id"));
               transaction.setTransactionDate(resultSet.getDate("t_date"));
               transaction.setReturnDate(resultSet.getDate("t_return_date"));
               transaction.setDueDate(resultSet.getDate("t_due_date"));
           }
       return transaction;

   }

    @POST
    @Path("/addtransaction/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTransaction(Transaction transaction) throws SQLException {

        Statement statement = connection.createStatement();

        if (transaction.getTransactionDate() ==null)
        {
            transaction.setTransactionDate(new Date());
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "insert into  devmgt_isg9251.transaction_status(d_id,u_id,ts_id,t_date,t_return_date,t_due_date)" +
                " values ('" + transaction.getDeviceId() + "' ,'" +transaction.getUserId()+"','"+transaction.getTransactionStatusId()+"','"+
                dateFormat.format(transaction.getTransactionDate()) + "','" +dateFormat.format(transaction.getReturnDate())
                + "','" + dateFormat.format(transaction.getDueDate()) + "')";

        statement.execute(query);
        return Response.ok().status(201).build();


    }


    @PUT
    @Path("/updatetransaction/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTransaction(Transaction transaction ,@PathParam("id") String id ) throws SQLException {

        Statement statement = connection.createStatement();

        String query =null;
        LinkedList<String> listColumns= new LinkedList<String>();
        LinkedList<String> listValues= new LinkedList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if( transaction.getDeviceId()!=null) {
            listColumns.add("d_id");
            listValues.add(transaction.getDeviceId());
        }
        if( transaction.getUserId()!=null) {
            listColumns.add("u_id");
            listValues.add(transaction.getUserId());
        }
        if( transaction.getTransactionStatusId()!=null) {
            listColumns.add("ts_id");
            listValues.add(transaction.getTransactionStatusId());
        }

        if( transaction.getTransactionDate()!=null) {
            listColumns.add("t_date");
            listValues.add(dateFormat.format(transaction.getTransactionDate()));
        }
        if( transaction.getReturnDate()!=null) {
            listColumns.add("t_return_date");
            listValues.add(dateFormat.format(transaction.getReturnDate()));
        }
        if( transaction.getDueDate()!=null) {
            listColumns.add("t_due_date");
            listValues.add(dateFormat.format(transaction.getDueDate()));
        }


        for (int x= 0;x<listColumns.size();x++) {

            if(x==0)
            {
                query = "update devmgt_isg9251.transaction set ";
            }

            if(x!=(listColumns.size()-1))
            {
                query = query + listColumns.get(x) + " = '";
                query = query + listValues.get(x) + "' , ";
            }
            else{
                query = query + listColumns.get(x) + " = '";
                query = query + listValues.get(x)+ "' WHERE t_id = " + id;
            }

        }

        if (listColumns.size()!=0) {
            statement.execute(query);
        }
        return Response.ok().status(200).build();


    }
    

    final void init() {

        try {
           InitialContext context = new InitialContext();
           DataSource dataSource = (DataSource)context.lookup("jdbc/deviceRepoDS");
           connection = dataSource.getConnection();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

}