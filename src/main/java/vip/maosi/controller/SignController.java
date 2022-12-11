package vip.maosi.controller;

import vip.maosi.entity.Account;
import vip.maosi.entity.ResCover;
import vip.maosi.service.Mapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册
 */
@WebServlet("/sign")
public class SignController extends HttpServlet {

    private Mapper mapper = new Mapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取参数
        String accountParam = req.getParameter("account");
        String passwordParam = req.getParameter("password");
        String roleIdParam = req.getParameter("roleId");
        String classIdParam = req.getParameter("classId");
        String usernameParam = req.getParameter("username");
        if (accountParam.equals("") || passwordParam.equals("")){
            ResCover.fail(resp,"账号密码不能为空");
            return;
        }

        if (passwordParam.matches("^[0-9a-zA-Z]{1,6}$")){
            ResCover.fail(resp,"密码不能少于6个字符且不能全为数字");
            return;
        }

        Account account = new Account();
        account.setAccount(accountParam);
        account.setClassId(classIdParam);
        account.setPassword(passwordParam);
        account.setRoleId(roleIdParam);
        account.setUsername(usernameParam);
        mapper.setUser(account);

        ResCover.success(resp,"注册成功");
    }
}
