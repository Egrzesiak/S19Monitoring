package pl.egrzesiak.S19Monitoring;

public class MinerStatus {
    private final String ip;
    private final String worker;
    private final String model;
    private final int[] chipTemp;
    private final float hashrate5s;
    private final String uptime;
    private final String status;

    public MinerStatus(String ip, String worker, String model, int[] chipTemp, float hashrate5s, String uptime, String status) {
        this.ip = ip;
        this.worker = worker;
        this.model = model;
        this.chipTemp = chipTemp;
        this.hashrate5s = hashrate5s;
        this.uptime = uptime;
        this.status = status;
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

    public String getStatus() {
        return status;
    }
}
