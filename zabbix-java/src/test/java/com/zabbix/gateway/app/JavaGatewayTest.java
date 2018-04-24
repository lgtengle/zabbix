package com.zabbix.gateway.app;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/16 9:39
 *
 * @author leiguang
 */
public class JavaGatewayTest {


    
    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket("localhost", 10052);
            socket.setKeepAlive(true);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            Map<String, Object> params = new HashMap<>();
            params.put("request", "java gateway jmx");
            params.put("jmx_endpoint", "service:jmx:rmi:///jndi/rmi://127.0.0.1:12345/jmxrmi");
            params.put("keys", new String[]{"jmx[\"java.lang:type=Memory\", \"HeapMemoryUsage.used\"]"});
            String content = new Gson().toJson(params);
            System.out.println(content.length());
            dos.write('Z');
            dos.write('B');
            dos.write('X');
            dos.write('D');
            dos.write('\1');
            dos.writeLong(content.length());
            dos.write(content.getBytes());
            dos.flush();
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String reply = null;
            while ((reply = br.readLine()) != null) {
                System.out.println("接收服务器的信息："+reply);
            }

            br.close();
            is.close();
            dos.close();
            os.close();
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

}
