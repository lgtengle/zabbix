package com.zabbix.gateway.app;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/17 18:20
 *
 * @author leiguang
 */
public class RetrieveItemsTest {

    public static void main(String[] args) throws InterruptedException {
        RetrieveItemsTest retrieveItemsTest = new RetrieveItemsTest();
        //retrieveItemsTest.testGetItemList();
        while (true){
            retrieveItemsTest.senderTest();
            Thread.sleep(2000);
        }
    }


    Map<String, Object> params = new HashMap<>();

    public void testGetItemList(){
        Socket socket = null;
        try {
            socket = new Socket("192.168.217.5", 10051);

            params.put("request", "active checks");
            params.put("host", "testPassActiveHost");
            String content = new Gson().toJson(params);
            OutputStream send = NetUtil.send(socket, content);

            InputStream is = NetUtil.receivePrint(socket);
            send.close();
            is.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    int i = 5 ;
    int ns = 77053975;

    /**
     * 发送数据到zabbix测试
     */
    public void senderTest(){
        String host = "192.168.217.5";
        Socket socket = null;
        try {
            socket = new Socket(host, 10051);
            params.put("request", "agent data");

            List<AgentData> data = new ArrayList<>();
            //for (int i = 0; i < 3; i++) {
                data.add(new AgentData("testPassActiveHost", "visourzie", String.valueOf(i++), System.currentTimeMillis() / 1000, ns));
            //}
            params.put("data", data);
            params.put("clock", System.currentTimeMillis() / 1000);
            params.put("ns", ns);

            NetUtil.sendAnPrint(socket, new Gson().toJson(params));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class AgentData{
        String host;

        String key;

        Object value;

        long clock;

        long ns;

        public AgentData(String host, String key, Object value, long clock, long ns) {
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
}
