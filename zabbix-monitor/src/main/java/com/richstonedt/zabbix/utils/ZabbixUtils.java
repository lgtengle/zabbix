package com.richstonedt.zabbix.utils;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/4/24 18:21
 *
 * @author leiguang
 */
public class ZabbixUtils {


    public static void send(Socket socket, String content) throws IOException {
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
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
    }


    public static String receive(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String reply = null;
        StringBuilder sb = new StringBuilder();
        while ((reply = br.readLine()) != null) {
            sb.append(reply);
        }
        return sb.toString();
    }

}
