package com.heshengda15.utils;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接
 */
public class JDBCUtils {
    private static String URL = null;
    private static String root = null;
    private static String psd = null;
    private static String DRIVER = null;

    static{
        //读取properties文件
        Properties properties = new Properties();
        InputStream inputStream = JDBCUtils.class.getResourceAsStream("/user.properties");
        try {
            properties.load(inputStream);
            URL = properties.getProperty("url");
            root = properties.getProperty("user");
            psd = properties.getProperty("password");
            DRIVER = properties.getProperty("driver");
            Class.forName(DRIVER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConn() throws SQLException {
        Connection connection = DriverManager.getConnection(URL,root,psd);
        return connection;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement){
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
