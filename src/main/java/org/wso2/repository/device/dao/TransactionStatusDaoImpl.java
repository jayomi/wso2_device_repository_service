package org.wso2.repository.device.dao;

import org.wso2.repository.device.model.TransactionStatus;
import org.wso2.repository.device.util.DB;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class TransactionStatusDaoImpl implements TransactionStatusDao {

    public String deleteTransactionStatus(String id) {

        String strResponse="";

        try{

            Connection con = DB.getConnection();
            Statement statement = con.createStatement();

            String strCount = "select  count(*) cnt from transaction where ts_id in (select  ts_id from transaction_status where ts_id =" + id +")";

            ResultSet resultSet = statement.executeQuery(strCount);

            resultSet.next();

            if(resultSet.getInt("cnt") == 0)
            {
                String query = "delete from transaction_status where ts_id =" +id;
                statement.execute(query);
                    strResponse="Successfully Deleted ";
                    return strResponse;
                }

                statement.close();
                con.close();

            }catch (SQLException e) {
                e.printStackTrace();
                strResponse="Failed.try Again.";

                return  strResponse;

            }finally {

                return strResponse;

            }

    }

    public LinkedList<TransactionStatus> getTransactionStatus(UriInfo parameters) {


        String strResponse="";
        LinkedList<TransactionStatus> stList=new LinkedList<TransactionStatus>();

        try {
            String tsId = parameters.getQueryParameters().getFirst("tsId");
            String tsName = parameters.getQueryParameters().getFirst("tsName");

            String options = null;

            Connection con= DB.getConnection();
            Statement statement=con.createStatement();
            String query ="select * from transaction_status";

            boolean firstPara = false;

            if (tsId !=null)
            {
                options = " ts_id = '" + tsId +"' ";
                firstPara =true;
            }

            if (tsName !=null)
            {
                if (firstPara==false) {
                    options = " ts_name = '" + tsName+ "' ";
                    firstPara = true;
                }else
                {
                    options = options +  " AND ts_name = '" + tsName + "' ";
                }

            }

            if(firstPara)
            {
                query = query + " Where " + options;
            }

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                TransactionStatus st=new TransactionStatus();
                st.setTransactionStatusId(resultSet.getString("ts_id"));
                st.setTransactionStatusName(resultSet.getString("ts_name"));
                stList.add(st);
            }

            strResponse="Ok,Executed the Query";
            return stList;

        }catch (Exception e) {
            e.printStackTrace();
            strResponse="Data Not Executed";
            System.out.println( strResponse);
            return stList;

        }finally{
            return stList;
        }


    }

    //Add Transaction Status
    public String addTransactionStatus(TransactionStatus transactionStatus) {

        String strResponse = null;
        try {

            Connection connection = DB.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into  transaction_status(ts_name) values ('" + transactionStatus.getTransactionStatusName() + "')";

            statement.executeUpdate(query);
            strResponse="Data Added";
            System.out.println("OK");

        } catch (Exception ee) {
            ee.printStackTrace();
            strResponse="Failed.Try Again.";

        }finally{

            return strResponse;
        }


    }

    public String updateTransactionStatus(TransactionStatus tstatus, String id) {


        String strResponse = null;
        try {

            Connection connection = DB.getConnection();
            Statement statement = connection.createStatement();

            String query =null;

            if( tstatus.getTransactionStatusName()!=null)
            {
                query = "update transaction_status set ts_name ='"+tstatus.getTransactionStatusName() +"' WHERE ts_id =" + id;
                statement.execute(query);
            }

            strResponse="Sucessfully Updated";
            return strResponse;

        } catch (SQLException e) {
            e.printStackTrace();
            strResponse="Failed.Try Again";
            return strResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            return strResponse;
        }


    }
}
