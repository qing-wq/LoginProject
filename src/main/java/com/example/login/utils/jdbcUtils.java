package com.example.login.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class jdbcUtils {
    static String driver = null;
    static String url = null;
    static String username = null;
    static String password = null;

    static {
        try {
            // 获取配置文件   !!!配置文件要放在resource目录下
            InputStream inputStream = jdbcUtils.class.getClassLoader().getResourceAsStream("mysql.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            driver = properties.getProperty("driver");
            System.out.println("driver"+driver);
            url = properties.getProperty("url");
            System.out.println("url" + url);
            username = properties.getProperty("username");
            System.out.println("username"+username);
            password = properties.getProperty("password");
            System.out.println("累了/(ㄒoㄒ)/~~");
            // 1.加载驱动
            Class.forName(driver);
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2.获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 3.释放资源
    public static void release(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
