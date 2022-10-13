/*
 * Based on:
 * Copyright (C) Andrew Smith 2012-2013 original: https://github.com/ckolivas/cgminer/blob/master/API.java
 *
 */

package pl.egrzesiak.S19Monitoring;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;

public class MinerStatus {
    private final String ip;
    private String model;
    private int[] chipTemp;
    private float hashrate5s;
    private String uptime;
    private String port;

    private String errorString;
    static private final int MAXRECEIVESIZE = 65535;

    public MinerStatus(String ip, String port) {
        this.ip = ip;
        this.port = port;
        if (!getData()) {
            this.model = "";
            this.chipTemp = new int[]{0, 0, 0};
            this.hashrate5s = 0.0F;
            this.uptime = "";
        }
    }

    public String getIp() {
        return ip;
    }

    public String getModel() {
        return model;
    }

    public int[] getChipTemp() {
        return chipTemp;
    }

    public float getHashrate5s() {
        return hashrate5s;
    }

    public String getUptime() {
        return uptime;
    }

    public Boolean getData() {
        var hm = new HashMap<String,String>();
        hm = getSocketData("stats", new String[]{"Type","temp2_1","temp2_2","temp2_3","GHS 5s","Elapsed"});

        this.model = hm.getOrDefault("Type","");
        this.chipTemp = new int[]{Integer.valueOf(hm.getOrDefault("temp2_1", "0")),
                Integer.valueOf(hm.getOrDefault("temp2_2", "0")),
                Integer.valueOf(hm.getOrDefault("temp2_3", "0"))
        };
        this.hashrate5s = Float.valueOf(hm.getOrDefault("GHS 5s","0.0F"));
        this.uptime = hm.getOrDefault("Elapsed","");

        System.out.println(errorString);
        System.out.println(hm);
        return true;
    }

    private HashMap<String,String> getSocketData(String cmd, String[] keys){
        InetAddress _ip;
        int _port;
        Socket socket = null;
        String[] returnString;
        StringBuffer sb = new StringBuffer();
        HashMap<String,String> returnHM = new HashMap<>();
        returnHM.put("Error","0");

        char buf[] = new char[MAXRECEIVESIZE];
        int len = 0;

        try {
            _ip = InetAddress.getByName(this.ip);
        } catch (UnknownHostException uhe) {
            errorString += "Unknown host " + this.ip + ": " + uhe + "  ";
            returnHM.put("Error",errorString);
            return returnHM;
        }

        try {
            _port = Integer.parseInt(this.port);
        } catch (NumberFormatException nfe) {
            errorString += "Invalid port " + port + ": " + nfe + "  ";
            returnHM.put("Error",errorString);
            return returnHM;
        }

        try {
            socket = new Socket(_ip, _port);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.print(cmd.toCharArray());
            ps.flush();

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            while (0x80085 > 0) {
                len = isr.read(buf, 0, MAXRECEIVESIZE);
                if (len < 1)
                    break;
                sb.append(buf, 0, len);
                if (buf[len - 1] == '\0')
                    break;
            }

            socket.close();
        } catch (IOException ioe) {
            errorString += ioe.toString();
            returnHM.put("Error",errorString);
            return returnHM;
        }

        String[] splitstring;
        String[] data = sb.toString().split(",");

        for (var s : data) {
            if(Arrays.stream(keys).anyMatch(s::contains)) {
                splitstring = s.split("=");
                try{
                    returnHM.put(splitstring[0],splitstring[1].replace("|",""));
                }
                catch (Exception ex){
                    returnHM.put("Error",ex.toString());
                    return returnHM;
                }
            }
        }
        return returnHM;
    }
}
