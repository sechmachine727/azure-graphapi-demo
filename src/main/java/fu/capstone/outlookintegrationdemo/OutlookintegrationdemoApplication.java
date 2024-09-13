package fu.capstone.outlookintegrationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OutlookintegrationdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutlookintegrationdemoApplication.class, args);
	}

}
