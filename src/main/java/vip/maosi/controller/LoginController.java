package vip.maosi.controller;


import vip.maosi.entity.Account;
import vip.maosi.entity.AccountDto;
import vip.maosi.entity.Class;
import vip.maosi.entity.ResCover;
import vip.maosi.entity.Role;
import vip.maosi.service.Mapper;
import vip.maosi.util.DataBaseTemplate;
import vip.maosi.util.ResultSetUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * 登录
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

    private Mapper mapper = new Mapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String accountParam = req.getParameter("account");
        String passwordParam = req.getParameter("password");
        if (accountParam == null || passwordParam == null){
            ResCover.fail(resp,"账号密码不能为空");
            return;
        }

        if (passwordParam.matches("^[0-9a-zA-Z]{1,6}$")){
            ResCover.fail(resp,"密码不能少于6个字符且不能全为数字");
            return;
        }
        ResultSet resultSet = mapper.getUser(accountParam);
        Account account = ResultSetUtil.readEntity(resultSet, Account.class);
        if (account == null) {
            // 登录失败
            ResCover.fail(resp,"用户不存在");
            return;
        }
        if (account.getPassword().equals(passwordParam)){
            // 登录成功
            AccountDto accountDto = new AccountDto();
            // 封装role
            ResultSet roleSet = mapper.getRole(account.getRoleId());
            Role role = ResultSetUtil.readEntity(roleSet, Role.class);
            accountDto.setRole(role);

            // 封装class
            ResultSet classSet = mapper.getClass(account.getClassId());
            Class aClass = ResultSetUtil.readEntity(classSet, Class.class);
            accountDto.setaClass(aClass);

            accountDto.setAccount(account.getAccount());
            accountDto.setClassId(account.getClassId());
            accountDto.setUsername(account.getUsername());
            accountDto.setId(account.getId());
            accountDto.setPoint(account.getPoint());
            ResCover.successData(resp,accountDto);
        }else {
            // 登录失败
            ResCover.fail(resp,"密码错误");
        }
    }


}
