package vip.maosi.controller;

import vip.maosi.entity.ResCover;
import vip.maosi.entity.Role;
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

@WebServlet("/role")
public class RoleController  extends HttpServlet {

    private Mapper mapper = new Mapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultSet roleList = mapper.getRoleList();
        List<Role> roles = ResultSetUtil.readList(roleList, Role.class);
        ResCover.successData(resp,roles);
    }
}
