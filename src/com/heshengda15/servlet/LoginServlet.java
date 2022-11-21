package com.heshengda15.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.heshengda15.bean.PageBean;
import com.heshengda15.bean.User;
import com.heshengda15.dao.UserDao;

/**
 * 登录页面逻辑操作
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
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
		UserDao userDAo = new UserDao();
		User user = userDAo.Login(name, password);
		if (user != null) {
			//获取所有记录数
			int countTotal = userDAo.countTotal();
			//实例化PageBean
			PageBean pageBean = new PageBean(1, countTotal,9);
			//查找分页数据
			List<User> list = userDAo.findAll(pageBean);
			//绑定值并转发
			request.setAttribute("list",list);
			request.setAttribute("pageBean", pageBean);
			request.getSession().setAttribute("name", name);

			//将用户名和密码添加到Cookie中
			//创建Cookie对象，将用户名和密码添加到Cookie中
			Cookie cookiename = new Cookie("name",name);
			Cookie cookiepwd = new Cookie("password",password);
			//设置Cookie的过期时间一周
			cookiename.setMaxAge(60*60*24*7);
			cookiepwd.setMaxAge(60*60*24*7);
			//发送Cookie给客户端
			response.addCookie(cookiename);
			response.addCookie(cookiepwd);
			request.getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
		} else {
			response.sendRedirect("defeat.jsp");
		}
	}
}
