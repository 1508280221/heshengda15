package com.heshengda15.servlet;

import com.heshengda15.bean.User;
import com.heshengda15.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册页面逻辑
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setGender(gender);
        user.setEmail(email);
        user.setBirthday(birthday);

        UserDao userDAo = new UserDao();
        userDAo.addUser(user);
        System.out.println("注册成功");
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }
}
