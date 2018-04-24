package com.zabbix.gateway.app;

import com.google.gson.Gson;
import com.zabbix.gateway.model.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/18 10:21
 *
 * @author leiguang
 */
public class CreateZabbixObjectTest {

    public static void main(String[] args) throws IOException {
        new CreateZabbixObjectTest().createItem();
    }


    Map<String, Object> data = new HashMap<>();

    public void createHost(){
        data.put("jsonrpc", "2.0");
        data.put("method", "host.create");
        PassActiveHost passActiveHost = new PassActiveHost();
        passActiveHost.setHost("testPassActiveHost");
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("15"));
        passActiveHost.setGroups(groups);

        List<Interface> interfaces = new ArrayList<>();
        interfaces.add(new Interface(1, 1, 1, "127.0.0.1", "", "10050"));
        passActiveHost.setInterfaces(interfaces);

        data.put("params", passActiveHost);

        data.put("auth", "a7ad708215ea7cd63a1a45bd24b3cbd3");
        data.put("id", "5");

        try {
            String content = new Gson().toJson(data);
            sendHttpRequest(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ZabbixApiParameter zabbixApiParameter = new ZabbixApiParameter("2.0", null, "a7ad708215ea7cd63a1a45bd24b3cbd3");
    public void createApplication() throws IOException {
        data.put("name", "passengerApp");
        data.put("hostid", "10257");
        zabbixApiParameter.setMethod("application.create");
        zabbixApiParameter.setParams(data);
        sendHttpRequest(new Gson().toJson(zabbixApiParameter));
    }

    public void createItem() throws IOException {
        zabbixApiParameter.setMethod("item.create");
        data.put("name", "createFirstItem");
        data.put("key_", "visourzie");
        data.put("hostid", "10257");
        data.put("type", 7);
        data.put("value_type", 3);
        data.put("applications", new String[]{"1060"});
        data.put("delay", "30s");
        zabbixApiParameter.setParams(data);
        sendHttpRequest(new Gson().toJson(zabbixApiParameter));
    }

    public void getToken() throws IOException {
        data.put("jsonrpc", "2.0");
        data.put("method", "user.login");
        data.put("id", "6");
        data.put("params", new User("Admin", "zabbix"));
        sendHttpRequest(new Gson().toJson(data));
    }

    public void sendHttpRequest(String content) throws IOException {
        System.out.println(content);
        URL url = new URL("http://192.168.217.5/zabbix/api_jsonrpc.php");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json-rpc");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        OutputStream os = conn.getOutputStream();
        os.write(content.getBytes());
        os.flush();


        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ( (line = br.readLine())!= null) {
            System.out.println("请求结果：" + line);
        }

        os.close();
        is.close();
        conn.disconnect();
    }
}
