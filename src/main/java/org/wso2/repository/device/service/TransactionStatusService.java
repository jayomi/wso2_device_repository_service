package org.wso2.repository.device.service;


import org.wso2.repository.device.dao.TransactionStatusDao;
import org.wso2.repository.device.dao.TransactionStatusDaoImpl;
import org.wso2.repository.device.model.TransactionStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.LinkedList;


@Path("/transactionstatus/")
public class TransactionStatusService
{

    TransactionStatusDao tsDao;



    @DELETE
    @Path("/deletetransactionstatus/{id}/")
    public Response deleteTransactionStatus(@PathParam("id") String id) throws Exception {

        String strResponse;
        tsDao=new TransactionStatusDaoImpl();
        strResponse=tsDao.deleteTransactionStatus(id);
        return Response.ok(strResponse).build();

    }

   @GET
   @Path("/gettransactionstatus/{id}/")
   @Produces(MediaType.APPLICATION_JSON)
   public LinkedList<TransactionStatus> getTransactionStatus(@Context UriInfo parameters) throws SQLException {

       LinkedList stList=new LinkedList();
       tsDao= new TransactionStatusDaoImpl();
       stList=tsDao.getTransactionStatus(parameters);
       return stList;



   }

    @POST
    @Path("/addtransactionstatus/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTransactionStatus(TransactionStatus transactionStatus) throws SQLException {

        String strResponse;
        tsDao=new TransactionStatusDaoImpl();
        strResponse=tsDao.addTransactionStatus(transactionStatus);
        return Response.ok(strResponse).build();

    }


    @PUT
    @Path("/updatetransactionstatus/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTransactionStatus(TransactionStatus transactionStatus ,@PathParam("id") String id ) throws SQLException {

        String strResponse="";
        tsDao=new TransactionStatusDaoImpl();
        strResponse=tsDao.updateTransactionStatus(transactionStatus,id);
        return Response.ok(strResponse).build();

    }


}