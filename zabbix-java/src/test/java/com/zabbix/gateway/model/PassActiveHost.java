package com.zabbix.gateway.model;

import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/18 10:31
 *
 * @author leiguang
 */
public class PassActiveHost {

    private String host;

    private List<Group> groups;

    private List<Interface> interfaces;

    private int inventory_mode;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public int getInventory_mode() {
        return inventory_mode;
    }

    public void setInventory_mode(int inventory_mode) {
        this.inventory_mode = inventory_mode;
    }
}
