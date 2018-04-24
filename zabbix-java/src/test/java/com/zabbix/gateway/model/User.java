package com.zabbix.gateway.model;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/18 11:32
 *
 * @author leiguang
 */
public class User {

    private String user;

    private String password;

    public User(){}
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
