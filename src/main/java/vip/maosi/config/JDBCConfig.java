package vip.maosi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCConfig {
    private static Connection conn = null;
    static{
        try {
            // 获得数据库连接配置
            ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
            String DRIVER = bundle.getString("driver");
            String URL = bundle.getString("url");
            String USER = bundle.getString("user");
            String PASSWORD = bundle.getString("password");
            // 加载驱动程序
            Class.forName(DRIVER);
            // 链接数据库
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 返回数据库链接
    public static Connection getConn() {
        return conn;
    }
}
