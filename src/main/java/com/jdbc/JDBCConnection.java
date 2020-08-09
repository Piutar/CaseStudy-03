package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static  String jdbcURL = "jdbc:mysql://localhost:3306/storage?useSSL=false";
    private static String jdbcUsername= "root";
    private static String jdbcPassword = "12345";

    public static Connection getJDBCConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("*******************Loi ket noi SQL**********");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("*******************Loi ket noi SQL**********");
            throwables.printStackTrace();
        }
        return connection;
    }
}
