package com.heshengda15.test;

import com.heshengda15.bean.User;
import com.heshengda15.dao.UserDao;

import java.util.Random;

public class UserDaoTest {
    public static void main(String[] args) {
        Random random = new Random(1000000);

        //测试表，随机生成10条数据
        for (int i=0; i<10; i++){
            UserDao us = new UserDao();
            User user = new User();
            user.setName("1"+random.nextInt(100));
            user.setPassword("110");
            user.setBirthday("200"+random.nextInt(10)+"-"+ random.nextInt(13)+"-"+random.nextInt(12));
            user.setEmail("wangxiang"+random.nextInt(1000)+"@qq.com");
            int x = random.nextInt(2);
            String s;
            if (x == 1){
                s = "男";
            } else {
                s = "女";
            }
            user.setGender(s);
            us.addUser(user);
        }

    }
}
