package vip.maosi.controller;

import vip.maosi.entity.Account;
import vip.maosi.entity.ResCover;
import vip.maosi.service.Mapper;
import vip.maosi.util.ResultSetUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@WebServlet("/infoClass")
public class InfoClassController extends HttpServlet {

    private Mapper mapper = new Mapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultSet list = mapper.getUserListByClassId(req.getParameter("id"));
        List<Account> accounts = ResultSetUtil.readList(list, Account.class);
        ResCover.successData(resp,accounts);
    }
}
