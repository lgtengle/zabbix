package com.zabbix.gateway.model;

import java.util.Random;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/18 11:35
 *
 * @author leiguang
 */
public class ZabbixApiParameter {

    private String jsonrpc;

    private String method;

    private String auth;

    private int id;

    private Object params;

    public ZabbixApiParameter(){}


    public ZabbixApiParameter(String jsonrpc, String method, String auth) {
        this(jsonrpc, method, auth, new Random().nextInt(1000));
    }

    public ZabbixApiParameter(String jsonrpc, String method, String auth, int id) {
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.auth = auth;
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
