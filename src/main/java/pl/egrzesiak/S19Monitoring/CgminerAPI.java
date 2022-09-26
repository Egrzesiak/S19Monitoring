package pl.egrzesiak.S19Monitoring;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class CgminerAPI {
    private String ip;
    private String port;
    private HashMap<String, String> minerData;
    private String errorString = "";
    static private final int MAXRECEIVESIZE = 65535;
    static private Socket socket = null;

    public CgminerAPI(String ip, String port){
        this.ip = ip;
        this.port = port;
        minerData = new HashMap<>();
        System.out.println("api object initialized");
    }

    public boolean getData() throws Exception{
        boolean error = false;

        System.out.println("GET DATA IP:" + this.ip + " PORT: " + port);
        InetAddress _ip;
        int _port;

        try {
            _ip = InetAddress.getByName(this.ip);
        }
        catch (UnknownHostException uhe) {
            errorString += "Unknown host " + this.ip + ": " + uhe + "  ";
            return true;
        }

        try {
            _port = Integer.parseInt(this.port);
        }
        catch (NumberFormatException nfe) {
            errorString += "Invalid port " + port + ": " + nfe + "  ";
            return true;
        }

        StringBuffer sb = new StringBuffer();
        char buf[] = new char[MAXRECEIVESIZE];
        char buf2[] = new char[MAXRECEIVESIZE];
        int len = 0;

        //pools
        try
        {
            socket = new Socket(_ip, _port);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.print("pools".toCharArray());
            ps.flush();

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            while (0x80085 > 0)
            {
                len = isr.read(buf, 0, MAXRECEIVESIZE);
                if (len < 1)
                    break;
                sb.append(buf, 0, len);
                if (buf[len-1] == '\0')
                    break;
            }

            PrintStream ps2 = new PrintStream(socket.getOutputStream());
            ps2.print("stats".toCharArray());
            ps2.flush();

            InputStreamReader isr2 = new InputStreamReader(socket.getInputStream());
            while (0x80085 > 0)
            {
                len = isr2.read(buf, 0, MAXRECEIVESIZE);
                if (len < 1)
                    break;
                sb.append(buf2, 0, len);
                if (buf2[len-1] == '\0')
                    break;
            }
            closeAll();
        }
        catch (IOException ioe)
        {
            errorString += ioe.toString();
            closeAll();
            return true;
        }

        String result = sb.toString();

        System.out.println("Answer='"+result+"'");
        System.out.println("Test: api get data successful");
        return error;
    }

    private void closeAll() throws Exception
    {
        if (socket != null)
        {
            socket.close();
            socket = null;
        }
    }

    public String getErrorString(){
        return errorString;
    }
}
