package com.richstonedt.zabbix;

import com.google.gson.Gson;
import com.richstonedt.zabbix.conf.Constant;
import com.richstonedt.zabbix.entity.AgentData;
import com.richstonedt.zabbix.entity.ItemInfo;
import com.richstonedt.zabbix.utils.ZabbixUtils;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 * description: 推送数据到zabbix-server
 * </p>
 * Created on 2018/4/24 18:05
 *
 * @author leiguang
 */
public class ItemInfoPusher {


    /**
     * zabbix server ip
     */
    private String server_ip;

    /**
     * zabbix server port
     */
    private int server_port;


    /**
     * monitor host name
     */
    private String host;


    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public int getServer_port() {
        return server_port;
    }

    public void setServer_port(int server_port) {
        this.server_port = server_port;
    }

    public void push(String key, Object value){
        if (value != null) {
            List<Object> values = new ArrayList<>();
            values.add(value);
            push(key, values);
        }
    }

    /**
     * 推送数据到zabbix-server
     * @param key
     * @param values
     */
    public void push(String key, List<Object> values){
        Socket socket = null;
        try {
            socket = new Socket(server_ip, server_port);
            AgentData agentData = new AgentData();
            agentData.setRequest(Constant.ZABBIX_PUSH_REQUEST);

            List<ItemInfo> data = new ArrayList<>(values.size());
            for (Object value : values) {
                data.add(new ItemInfo(host, key, value, System.currentTimeMillis() / 1000, 0));
            }
            agentData.setClock(System.currentTimeMillis() / 1000);
            agentData.setNs(0);

            String content = new Gson().toJson(agentData);
            ZabbixUtils.send(socket, content);
            String receive = ZabbixUtils.receive(socket);
            socket.close();
            System.out.println(receive);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (socket!=null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
