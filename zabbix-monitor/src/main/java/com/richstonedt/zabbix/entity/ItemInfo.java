package com.richstonedt.zabbix.entity;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/24 18:11
 *
 * @author leiguang
 */
public class ItemInfo {

    String host;

    String key;

    Object value;

    long clock;

    long ns;

    public ItemInfo(){}
    public ItemInfo(String host, String key, Object value, long clock, long ns) {
        this.host = host;
        this.key = key;
        this.value = value;
        this.clock = clock;
        this.ns = ns;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
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
