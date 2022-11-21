package com.heshengda15.servlet;

import com.heshengda15.bean.PageBean;
import com.heshengda15.bean.User;
import com.heshengda15.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        UserDao userDao = new UserDao();
        String content = req.getParameter("content");
        System.out.println(content);
        if (content != null && !" ".equals(content)) {
            int totalCount = userDao.countTotal(content);
            PageBean pageBean = new PageBean(Integer.parseInt(req.getParameter("pageNo")), totalCount, 9);
            //        计算总页数
            if (totalCount % pageBean.getPageSize() == 0) {
                pageBean.setPageTotal(totalCount / pageBean.getPageSize());
            } else {
                pageBean.setPageTotal(totalCount / pageBean.getPageSize() + 1);
            }

            //计算当前页(第一次访问的即空位第一页)
            if (req.getParameter("pageNo") == null || req.getParameter("pageNo") == "") {
                pageBean.setPageNo(1);
            } else {
                //如果现在的页数大于总页数，当前页数就是总页数
                if (Integer.parseInt(req.getParameter("pageNo")) > pageBean.getPageTotal()) {
                    pageBean.setPageNo(pageBean.getPageTotal());
                } else {
                    pageBean.setPageNo(Integer.parseInt(req.getParameter("pageNo")));
                }
            }
            pageBean.setStartRow((pageBean.getPageNo() - 1) * 10);
            req.setAttribute("pageBean", pageBean);
            List<User> list = userDao.findAll(pageBean, content);
            req.setAttribute("list", list);
        } else {
            int totalCount = userDao.countTotal();
            PageBean pageBean = new PageBean(Integer.parseInt(req.getParameter("pageNo")), totalCount, 10);
            //        计算总页数
            if (totalCount % pageBean.getPageSize() == 0) {
                pageBean.setPageTotal(totalCount / pageBean.getPageSize());
            } else {
                pageBean.setPageTotal(totalCount / pageBean.getPageSize() + 1);
            }

            //计算当前页
            if (req.getParameter("pageNo") == null || req.getParameter("pageNo") == "") {
                pageBean.setPageNo(1);
            } else {
                //如果现在的页数大于总页数，当前页数就是总页数
                if (Integer.parseInt(req.getParameter("pageNo")) > pageBean.getPageTotal()) {
                    pageBean.setPageNo(pageBean.getPageTotal());
                } else {
                    pageBean.setPageNo(Integer.parseInt(req.getParameter("pageNo")));
                }

            }
            pageBean.setStartRow((pageBean.getPageNo() - 1) * 10);
            req.setAttribute("pageBean", pageBean);
            List<User> list = userDao.findAll(pageBean);
            req.setAttribute("list", list);
        }
        req.getRequestDispatcher("/WEB-INF/success.jsp").forward(req, resp);
    }
}
