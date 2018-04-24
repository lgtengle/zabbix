package com.richstonedt.zabbix.entity;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/24 18:12
 *
 * @author leiguang
 */
public class AgentData {

    private String request;

    private Object data;

    private long clock;

    private long ns;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getClock() {
        return clock;
    }

    public void setClock(long clock) {
        this.clock = clock;
    }

    public long getNs() {
        return ns;
    }

    public void setNs(long ns) {
        this.ns = ns;
    }
}
