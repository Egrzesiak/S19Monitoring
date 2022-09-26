package pl.egrzesiak.S19Monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S19MonitoringApplication {
	CgminerAPI cgminerAPI;

	public static  void main(String[] args) {
		SpringApplication.run(S19MonitoringApplication.class, args);
	}

	public void GetMinersData() throws Exception {
		String command = "summary";
		String ip = "127.0.0.1";
		String port = "4028";

		//cgminerAPI = new CgminerAPI(command, ip, port);
	}
}
