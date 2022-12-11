package vip.maosi.config;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CorsFilter",value = "/*")
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response1= (HttpServletResponse) response;
        response1.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(request, response);
    }
}