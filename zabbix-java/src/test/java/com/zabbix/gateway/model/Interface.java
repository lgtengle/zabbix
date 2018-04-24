package com.zabbix.gateway.model;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/18 11:35
 *
 * @author leiguang
 */
public class Interface {

    private int type;

    private int main;

    private int useip;

    private String ip;

    private String dns;

    private String port;

    public Interface(){}

    public Interface(int type, int main, int userip, String ip, String dns, String port) {
        this.type = type;
        this.main = main;
        this.useip = userip;
        this.ip = ip;
        this.dns = dns;
        this.port = port;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public int getUseip() {
        return useip;
    }

    public void setUseip(int useip) {
        this.useip = useip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
