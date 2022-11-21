package com.heshengda15.bean;

//用户管理工具类
public class User {
    private int id;
    private String name;
    private String password;
    private String gender;
    private String birthday;
    private String email;
    public User(){}
    public User(int id, String name, String password, String gender, String birthday, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
