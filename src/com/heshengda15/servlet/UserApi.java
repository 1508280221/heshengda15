package com.heshengda15.servlet;

import com.heshengda15.bean.User;
import com.heshengda15.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UserApi", urlPatterns = "/userApi")
public class UserApi extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getParameter("method");
        /**
         * 添加用户
         */
        if ("addUser".equals(method)) {
            //获取表单字符串
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            String birthday = request.getParameter("birthday");
            //创建user对象实例，传入值
            User user = new User();
            user.setGender(gender);
            user.setEmail(email);
            user.setBirthday(birthday);
            user.setName(name);
            user.setPassword(password);
            userDao.addUser(user);
            response.sendRedirect("/userServlet?pageNo=1");
        }

        if ("modUser".equals(method)) {
            //获取表单字符串
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            String birthday = request.getParameter("birthday");
            //创建user对象实例，传入值
            User user = new User();
            user.setId(Integer.valueOf(id));
            user.setGender(gender);
            user.setEmail(email);
            user.setBirthday(birthday);
            user.setName(name);
            user.setPassword(password);
            userDao.update(user);
            response.sendRedirect("/userServlet?pageNo=1");
        }
        if ("delUser".equals(method)) {
            String id = request.getParameter("id");
            userDao.delete(Integer.valueOf(id));
            response.sendRedirect("/userServlet?pageNo=1");
        }

    }
}
