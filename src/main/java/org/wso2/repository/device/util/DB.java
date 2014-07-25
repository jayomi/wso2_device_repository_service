package org.wso2.repository.device.util;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DB {

    public static Connection getConnection() throws Exception{
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("jdbc/deviceRepoDS");
        return dataSource.getConnection();


    }

}