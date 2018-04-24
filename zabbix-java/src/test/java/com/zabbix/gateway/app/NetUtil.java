package com.zabbix.gateway.app;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/18 10:24
 *
 * @author leiguang
 */
public class NetUtil {

    public static OutputStream send(Socket socket, String content) throws IOException {
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        Map<String, String> params = new HashMap<>();
        params.put("request", "active checks");
        params.put("host", "192.168.217.5");
        dos.write('Z');
        dos.write('B');
        dos.write('X');
        dos.write('D');
        dos.write('\1');

        long v = content.length();
        byte writeBuffer[] = new byte[8];
        writeBuffer[7] = (byte)(v >>> 56);
        writeBuffer[6] = (byte)(v >>> 48);
        writeBuffer[5] = (byte)(v >>> 40);
        writeBuffer[4] = (byte)(v >>> 32);
        writeBuffer[3] = (byte)(v >>> 24);
        writeBuffer[2] = (byte)(v >>> 16);
        writeBuffer[1] = (byte)(v >>>  8);
        writeBuffer[0] = (byte)(v >>>  0);

        dos.write(writeBuffer);
        dos.write(content.getBytes());
        dos.flush();
        socket.shutdownOutput();
        return os;
    }


    public static InputStream receivePrint(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String reply = null;
        while ((reply = br.readLine()) != null) {
            System.out.println("接收服务器的信息："+reply);
        }
        return is;
    }

    public static void sendAnPrint(Socket socket, String content) throws IOException {
        System.out.println(content);
        OutputStream send = NetUtil.send(socket, content);
        InputStream is = NetUtil.receivePrint(socket);
        send.close();
        is.close();
        socket.close();
    }
}
