package vip.maosi.entity;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回体
 */
public class ResCover {
    public static void success(HttpServletResponse response,String msg) {
        Res res = new Res();
        res.setCode(200);
        res.setMsg(msg);
        resUtil(response, JSON.toJSON(res));
    }

    public static void successData(HttpServletResponse response,Object data) {
        Res res = new Res();
        res.setCode(200);
        res.setData(data);
        res.setMsg("成功");
        resUtil(response, JSON.toJSON(res));
    }

    public static void fail(HttpServletResponse response,String msg) {
        Res res = new Res();
        res.setCode(-1);
        res.setMsg(msg);
        resUtil(response, JSON.toJSON(res));
    }

    public static void failData(HttpServletResponse response,Object data) {
        Res res = new Res();
        res.setCode(-1);
        res.setData(data);
        res.setMsg("失败");
        resUtil(response, JSON.toJSON(res));
    }

    private static void resUtil(HttpServletResponse response, Object res) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            response.getWriter().println(res);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
