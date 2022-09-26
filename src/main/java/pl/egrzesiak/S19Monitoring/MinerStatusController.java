package pl.egrzesiak.S19Monitoring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MinerStatusController {

    @GetMapping("/data")
    public MinerStatus MinerStatusController(
            @RequestParam(value = "ip", defaultValue = "192.168.1.1") String ip,
            @RequestParam(value = "port", defaultValue = "4028") String port) {
        int[] array = {60,60,60};
        MinerStatus minerStatus = new MinerStatus(ip, port);
        minerStatus.getData();
        return minerStatus;
    }
}
