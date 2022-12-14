package com.heshengda15.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.heshengda15.dao.UserDao;

/**
 * 校验注册页面
 */
@WebServlet(name = "checkNameApi")
public class CheckNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        if ("".equals(name) || "".equals(name.trim())) {
            out.println("<font color=red>用户名不能为空</font>");
            System.out.println("用户名不能为空");
        } else {
            UserDao userDAo = new UserDao();
            boolean flag = userDAo.isExist(name);
            if (!flag) {
                out.println("<font color=red>用户名已存在</font>");
                System.out.println("用户名已存在");
            }
        }
    }
}
