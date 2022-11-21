package com.heshengda15.servlet;

import com.alibaba.fastjson.JSON;
import com.heshengda15.bean.User;
import com.heshengda15.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户传递数据
 */
@WebServlet(name = "GetUser", urlPatterns = "/getUserApi")
public class GetUser extends HttpServlet {
    UserDao userDao = new UserDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter writer = response.getWriter();
        UserDao userDao = new UserDao();
        User user = userDao.getUserById(id);
        System.out.println(JSON.toJSON(user));
        writer.println(JSON.toJSON(user));
        writer.flush();
        writer.close();
    }
}
