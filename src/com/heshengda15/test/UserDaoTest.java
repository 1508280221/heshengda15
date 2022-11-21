package com.heshengda15.test;

import com.heshengda15.bean.User;
import com.heshengda15.dao.UserDao;

public class UserDaoTest {
    public static void main(String[] args) {
        for (int i=0; i<10; i++){
            UserDao us = new UserDao();
            User user = new User();
            user.setName("123");
            user.setPassword("123");
            user.setBirthday("2001-01-01");
            user.setEmail("123");
            user.setGender("女");
            us.addUser(user);
        }

    }
}
