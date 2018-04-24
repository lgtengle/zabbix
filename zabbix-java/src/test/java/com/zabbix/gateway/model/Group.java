package com.zabbix.gateway.model;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/18 10:43
 *
 * @author leiguang
 */
public class Group {

    private String groupid;

    public Group(){

    }
    public Group(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
