package org.wso2.repository.device.service;


import org.wso2.repository.device.data.TransactionStatus;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Path("/transactionstatus/")
public class TransactionStatusService
{

    Connection connection;

    public TransactionStatusService() {
        init();
    }





    @DELETE
    @Path("/deletetransactionstatus/{id}/")
    public Response deleteTransactionStatus(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


        Statement statement = connection.createStatement();
        String strCount = "select  count(*) cnt from devmgt_isg9251.transaction where ts_id in (select  ts_id from devmgt_isg9251.transaction_status where ts_id =" + id +")";

        ResultSet resultSet = statement.executeQuery(strCount);

        resultSet.next();

        if(resultSet.getInt("cnt") == 0)
        {
            String query = "delete from devmgt_isg9251.transaction_status where ts_id =" +id;
            statement.execute(query);
            return Response.ok().status(200).build();

        }
        else
        {
            return Response.ok().status(405).build();

        }


    }

   @GET
   @Path("/gettransactionstatus/{id}/")
   @Produces(MediaType.APPLICATION_JSON)
   public TransactionStatus getTransactionStatus(@PathParam("id") String id) throws SQLException {

        int intId = Integer.parseInt(id);


           Statement statement = connection.createStatement();
           String query = "select * from devmgt_isg9251.transaction_status where ts_id =" +id;
           ResultSet resultSet = statement.executeQuery(query);

       TransactionStatus transactionStatus = new TransactionStatus();

           while (resultSet.next()) {
               transactionStatus.setTransactionStatusId(resultSet.getString("ts_id"));
               transactionStatus.setTransactionStatusName(resultSet.getString("ts_name"));
           }
       return transactionStatus;

   }

    @POST
    @Path("/addtransactionstatus/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTransactionStatus(TransactionStatus transactionStatus) throws SQLException {

        Statement statement = connection.createStatement();

        String query = "insert into  devmgt_isg9251.transaction_status(ts_name) values ('" + transactionStatus.getTransactionStatusName() + "')";

        statement.execute(query);
        return Response.ok().status(201).build();


    }


    @PUT
    @Path("/updatetransactionstatus/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTransactionStatus(TransactionStatus transactionStatus ,@PathParam("id") String id ) throws SQLException {

        Statement statement = connection.createStatement();

        String query =null;

        if( transactionStatus.getTransactionStatusName()!=null)
        {
            query = "update devmgt_isg9251.transaction_status set ts_name ='"+transactionStatus.getTransactionStatusName() +"' WHERE ts_id =" + id;
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