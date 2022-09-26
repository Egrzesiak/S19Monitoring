package pl.egrzesiak.S19Monitoring;

public class MinerStatus {
    private final String ip;
    private final String worker;
    private final String model;
    private final int[] chipTemp;
    private final float hashrate5s;
    private final String uptime;
    private final String status;
    private CgminerAPI cgminerAPI;
    private String port;

    public MinerStatus(String ip, String port) {
        this.ip = ip;
        this.port = port;
        this.worker = "";
        this.model = "";
        this.chipTemp = new int[]{0, 0, 0};
        this.hashrate5s = 0.0F;
        this.uptime = "";
        this.status = "";
    }

    public String getIp() {
        return ip;
    }

    public String getWorker() {
        return worker;
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

    public String getData() {
        cgminerAPI = new CgminerAPI(this.ip, this.port);
        try {
            cgminerAPI.getData();
        }
        catch(Exception ex){
            System.out.println(ex.toString());
            System.out.println(cgminerAPI.getErrorString());
            return cgminerAPI.getErrorString();
        }
        System.out.println(cgminerAPI.getErrorString());
        System.out.println("Test: Data");
        return status;
    }
}
