package vip.maosi.util;
import vip.maosi.config.JDBCConfig;
import java.sql.*;

/**
 * 数据库链接模板
 */
public class DataBaseTemplate {

    public ResultSet exec(String sql) {
        ResultSet resultSet = null;
        try{
            Connection connection = this.getConnection();
            resultSet = this.execSql(connection,sql);
            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return resultSet;
        }
    }

    public ResultSet exec(String sql,Boolean query) {
        ResultSet resultSet = null;
        try{
            Connection connection = this.getConnection();
            resultSet = this.execSql(connection,sql,query);
            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return resultSet;
        }
    }

    private Connection getConnection() {
        return JDBCConfig.getConn();
    }

    private ResultSet execSql(Connection connection,String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    private ResultSet execSql(Connection connection,String sql, Boolean query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (query) {
            return preparedStatement.executeQuery();
        }else {
            preparedStatement.execute();
            return null;
        }
    }

    private void printResult(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            System.out.println(id);
            System.out.println(name);
        }
    }
}