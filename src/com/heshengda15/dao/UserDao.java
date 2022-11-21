package com.heshengda15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heshengda15.bean.PageBean;
import com.heshengda15.bean.User;
import com.heshengda15.utils.JDBCUtils;

public class UserDao {
    /**
     * 用户登录
     *
     */
    public User Login(String name, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = JDBCUtils.getConn();
            String sql = "select * from user where name=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, preparedStatement, resultSet);
        }
        return user;
    }

    /**
     * 添加用户
     *
     */
    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConn();
            String sql = "insert into user(name,password,gender,email,birthday) values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getGender());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getBirthday());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, preparedStatement);
        }
    }

    /**
     * 查询用户是否已注册
     *
     */
    public boolean isExist(String username) {
        Connection connection = null;
        String sql = "select * from user where name=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConn();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 查询所有
     *
     */
    public List<User> findAll(PageBean pageBean) {
        String sql = "select * from user limit ?,?";
        Object[] params = new Object[]{pageBean.getStartRow(), pageBean.getPageSize()};
        ResultSet rs = executeQuery(sql, params);
        ArrayList<User> list = new ArrayList<>();
        User user = null;
        try {
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("gender"), rs.getString("birthday"), rs.getString("email"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return list;
    }


    public User getUserById(String id) {
        String sql = "select * from user where id = ?";
        Object[] params = new Object[]{id};
        ResultSet rs = executeQuery(sql, params);
        User user = null;
        try {
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("gender"), rs.getString("birthday"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return user;
    }

    public List<User> findAll(PageBean pageBean, String content) {
        String sql = "select * from user where name like ? limit ?,?";
        Object[] params = new Object[]{"%" + content + "%", pageBean.getStartRow(), pageBean.getPageSize()};
        ResultSet rs = executeQuery(sql, params);
        ArrayList<User> list = new ArrayList<>();
        User user = null;
        try {
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("gender"), rs.getString("birthday"), rs.getString("email"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * 获取总记录数
     */
    public int countTotal() {
        String sql = "select count(*) as count from user";
        ResultSet rs = executeQuery(sql, null);
        int a = 0;
        try {
            while (rs.next()) {
                a = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 获取总记录数
     *
     */
    public int countTotal(String content) {
        String sql = "select count(*) as count from user where name like ?";
        ResultSet rs = executeQuery(sql, "%" + content + "%");
        int a = 0;
        try {
            while (rs.next()) {
                a = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public ResultSet executeQuery(String sql, Object... params) {//连接对象
        Connection conn = null;
        PreparedStatement pstmt = null;//结果集对象
        ResultSet rs = null;//处理SQL语句
        try {
            conn = JDBCUtils.getConn();//执行SQL对象
            pstmt = conn.prepareStatement(sql);//判断传入的参数是否为空
            if (params != null) {//循环赋值
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void update(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConn();
            String sql = "update user set name=?,password=?,gender=?,email=?,birthday=? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getGender());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getBirthday());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, preparedStatement);
        }
    }

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConn();
            String sql = "delete from user  where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, preparedStatement);
        }
    }
}
