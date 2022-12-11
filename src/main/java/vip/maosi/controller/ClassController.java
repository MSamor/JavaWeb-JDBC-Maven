package vip.maosi.controller;

import vip.maosi.entity.Class;
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

@WebServlet("/class")
public class ClassController extends HttpServlet {

    private Mapper mapper = new Mapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultSet classList = mapper.getClassList();
        List<Class> classLists = ResultSetUtil.readList(classList, Class.class);
        ResCover.successData(resp,classLists);
    }
}
