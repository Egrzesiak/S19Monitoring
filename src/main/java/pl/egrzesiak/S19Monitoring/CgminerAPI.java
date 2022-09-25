/*
 *
 * Copyright (C) Andrew Smith 2012-2013 original: https://github.com/ckolivas/cgminer/blob/master/API.java
 *
 */

package pl.egrzesiak.S19Monitoring;

import java.net.*;
import java.io.*;

public class CgminerAPI
{
    static private final int MAXRECEIVESIZE = 65535;
    static private String result1 = "'STATUS=S,When=1664014875,Code=11,Msg=Summary,Description=cgminer 1.0.0|SUMMARY,Elapsed=906695,GHS 5s=107840.10,GHS av=105358.03,GHS 30m=105201.68,Found Blocks=0,Getwork=32335,Accepted=45359,Rejected=27,Hardware Errors=24,Utility=3.00,Discarded=486635,Stale=1,Get Failures=2,Local Work=520579,Remote Failures=1,Network Blocks=1476,Total MH=95488086723488.19,Work Utility=1463272.92,Difficulty Accepted=22099525632.00,Difficulty Rejected=12845056.00,Difficulty Stale=0.00,Best Share=44101388095,Device Hardware%=0.00,Device Rejected%=0.00,Pool Rejected%=0.00,Pool Stale%=0.00,Last getwork=1664014875|'";
    static private String result2 = "'STATUS=S,When=1664014875,Code=7,Msg=3 Pool(s),Description=cgminer 1.0.0|POOL=0,URL=stratum+tcp://sha256.poolbinance.com:443,Status=Alive,Priority=0,Quota=1,Long Poll=N,Getworks=32326,Accepted=45359,Rejected=27,Discarded=486631,Stale=1,Get Failures=2,Remote Failures=1,User=ZKmine.Home001,Last Share Time=0:00:34,Diff=524K,Diff1 Shares=0,Proxy Type=,Proxy=,Difficulty Accepted=22099525632.00,Difficulty Rejected=12845056.00,Difficulty Stale=0.00,Last Share Difficulty=524288.00,Has Stratum=true,Stratum Active=true,Stratum URL=sha256.poolbinance.com,Has GBT=false,Best Share=44101388095.00,Pool Rejected%=0.00,Pool Stale%%=0.00|,POOL=1,URL=stratum+tcp://bs.poolbinance.com:3333,Status=Alive,Priority=1,Quota=1,Long Poll=N,Getworks=4,Accepted=0,Rejected=0,Discarded=0,Stale=0,Get Failures=0,Remote Failures=0,User=ZKmine.Home001,Last Share Time=0,Diff=131K,Diff1 Shares=0,Proxy Type=,Proxy=,Difficulty Accepted=0.00,Difficulty Rejected=0.00,Difficulty Stale=0.00,Last Share Difficulty=0.00,Has Stratum=true,Stratum Active=false,Stratum URL=,Has GBT=false,Best Share=0.00,Pool Rejected%=0.00,Pool Stale%%=0.00|,POOL=2,URL=stratum+tcp://sha256.poolbinance.com:8888,Status=Alive,Priority=2,Quota=1,Long Poll=N,Getworks=5,Accepted=0,Rejected=0,Discarded=4,Stale=0,Get Failures=0,Remote Failures=0,User=ZKmine.Home001,Last Share Time=0,Diff=131K,Diff1 Shares=0,Proxy Type=,Proxy=,Difficulty Accepted=0.00,Difficulty Rejected=0.00,Difficulty Stale=0.00,Last Share Difficulty=0.00,Has Stratum=true,Stratum Active=false,Stratum URL=,Has GBT=false,Best Share=0.00,Pool Rejected%=0.00,Pool Stale%%=0.00|'";
    static private String result3 = "'STATUS=S,When=1664014875,Code=9,Msg=1 ASC(s),Description=cgminer 1.0.0|ASC=0,Name=BTM_SOC,ID=0,Enabled=Y,Status=Alive,Tenperature=0.00,MHS av=0.00,MHS 5s=0.00,Accepted=45359,Rejected=27,Hardware Errors=0,Utility=0.00,Last Share Pool=0,Last Share Time=1664014841,Total MH=0.00,Diff1 Work=0,Difficulty Accepted=22099525632,Difficulty Rejected=12845056,Last Share Difficulty=1664014841,Last Valid Work=1664014841,Device Hardware%=0.00,Device Rejected%=0.00,Device Elapsed=906695|'";
    static private String result4 = "'STATUS=S,When=1664014876,Code=70,Msg=CGMiner stats,Description=cgminer 1.0.0|BMMiner=1.0.0,Miner=uart_trans.1.3,CompileTime=Wed Aug 25 17:40:50 CST 2021,Type=Antminer S19j Pro|,STATS=0,ID=BTM_SOC0,Elapsed=906695,Calls=0,Wait=0,Max=0,Min=99999999,GHS 5s=103376.08,GHS av=105357.89,rate_30m=105275.29,Mode=2,miner_count=3,frequency=545,fan_num=4,fan1=6000,fan2=6000,fan3=6000,fan4=6000,temp_num=3,temp1=79,temp2_1=84,temp2=79,temp2_2=84,temp3=79,temp2_3=84,temp_pcb1=79-79-61-61,temp_pcb2=79-76-60-61,temp_pcb3=79-78-61-61,temp_pcb4=0-0-0-0,temp_chip1=84-84-66-66,temp_chip2=84-81-65-66,temp_chip3=84-83-66-66,temp_chip4=0-0-0-0,temp_pic1=69-69-51-51,temp_pic2=69-66-50-51,temp_pic3=69-68-51-51,temp_pic4=0-0-0-0,total_rateideal=104000.00,rate_unit=GH,total_freqavg=545,total_acn=378,total rate=105357.89,temp_max=0,no_matching_work=24,chain_acn1=126,chain_acn2=126,chain_acn3=126,chain_acn4=0,chain_acs1= ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo,chain_acs2= ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo,chain_acs3= ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo ooo,chain_acs4=,chain_hw1=5,chain_hw2=9,chain_hw3=10,chain_hw4=0,chain_rate1=34480.68,chain_rate2=33820.98,chain_rate3=35074.42,chain_rate4=,freq1=545,freq2=545,freq3=545,freq4=0,miner_version=uart_trans.1.3,miner_id=no miner id now|'";
    static private Socket socket = null;

    private void closeAll() throws Exception
    {
        if (socket != null)
        {
            socket.close();
            socket = null;
        }
    }

    public void display(String result) throws Exception
    {
        String value;
        String name;
        String[] sections = result.split("\\|", 0);

        for (int i = 0; i < sections.length; i++)
        {
            if (sections[i].trim().length() > 0)
            {
                String[] data = sections[i].split(",", 0);

                for (int j = 0; j < data.length; j++)
                {
                    String[] nameval = data[j].split("=", 2);

                    if (j == 0)
                    {
                        if (nameval.length > 1
                                &&  Character.isDigit(nameval[1].charAt(0)))
                            name = nameval[0] + nameval[1];
                        else
                            name = nameval[0];

                        System.out.println("[" + name + "] =>");
                        System.out.println("(");
                    }

                    if (nameval.length > 1)
                    {
                        name = nameval[0];
                        value = nameval[1];
                    }
                    else
                    {
                        name = "" + j;
                        value = nameval[0];
                    }

                    System.out.println("   ["+name+"] => "+value);
                }
                System.out.println(")");
            }
        }
    }

    public void process(String cmd, InetAddress ip, int port) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        char buf[] = new char[MAXRECEIVESIZE];
        int len = 0;

        System.out.println("Attempting to send '"+cmd+"' to "+ip.getHostAddress()+":"+port);

        try
        {
            socket = new Socket(ip, port);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.print(cmd.toCharArray());
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

            closeAll();
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.toString());
            closeAll();
            return;
        }

        String result = sb.toString();

        System.out.println("Answer='"+result+"'");

        display(result);
    }

    public CgminerAPI(String command, String _ip, String _port) throws Exception
    {
        InetAddress ip;
        int port;

        try
        {
            ip = InetAddress.getByName(_ip);
        }
        catch (UnknownHostException uhe)
        {
            System.err.println("Unknown host " + _ip + ": " + uhe);
            return;
        }

        try
        {
            port = Integer.parseInt(_port);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Invalid port " + _port + ": " + nfe);
            return;
        }

        process(command, ip, port);
    }
}