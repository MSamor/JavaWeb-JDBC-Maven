package vip.maosi.service;

import vip.maosi.entity.Account;
import vip.maosi.util.DataBaseTemplate;

import java.sql.ResultSet;

public class Mapper {

    /**
     * 获取用户信息
     * @param accountParam
     * @return
     */
    public ResultSet getUser (String accountParam) {
        // 查询数据库
        DataBaseTemplate dataBaseTemplate = new DataBaseTemplate();
        return dataBaseTemplate.exec("SELECT * FROM account WHERE account = \"" + accountParam +"\"");
    }

    /**
     * 注册用户
     * @return
     */
    public ResultSet setUser (Account account) {
        // 查询数据库
        DataBaseTemplate dataBaseTemplate = new DataBaseTemplate();
        return dataBaseTemplate.exec("INSERT INTO account (account, password,role_id,username,class_id) VALUES (\""+account.getAccount()+"\",\""+account.getPassword()+"\","+account.getRoleId()+",\""+account.getUsername()+"\","+account.getClassId()+")",false);
    }

    /**
     * 获取角色信息
     * @param id
     * @return
     */
    public ResultSet getRole (String id) {
        // 查询数据库
        DataBaseTemplate dataBaseTemplate = new DataBaseTemplate();
        return dataBaseTemplate.exec("SELECT * FROM role WHERE id = \"" + id +"\"");
    }

    /**
     * 获取角色列表
     * @return
     */
    public ResultSet getRoleList () {
        DataBaseTemplate dataBaseTemplate = new DataBaseTemplate();
        return dataBaseTemplate.exec("SELECT * FROM role");
    }

    /**
     * 获取班级信息
     * @param id
     * @return
     */
    public ResultSet getClass (String id) {
        DataBaseTemplate dataBaseTemplate = new DataBaseTemplate();
        ResultSet resultSet = dataBaseTemplate.exec("SELECT * FROM class WHERE id = \"" + id +"\"");
        return resultSet;
    }

    /**
     * 获取班级列表
     * @return
     */
    public ResultSet getClassList () {
        DataBaseTemplate dataBaseTemplate = new DataBaseTemplate();
        return dataBaseTemplate.exec("SELECT * FROM class");
    }

    /**
     * 根据班级id获取本班所有列表
     * @return
     */
    public ResultSet getUserListByClassId (String id) {
        DataBaseTemplate dataBaseTemplate = new DataBaseTemplate();
        return dataBaseTemplate.exec("SELECT account,username FROM account WHERE class_id = " + id + " AND role_id = 3 OR role_id = 2");
    }
}
