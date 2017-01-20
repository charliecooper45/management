package uk.cooperca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ManagementApplication {

	@Autowired
	private Environment env;

	public static void setDefaultProfile(SpringApplication app) {
		Map<String, Object> defProperties =  new HashMap<>();
		defProperties.put("spring.profiles.default", "dev");
		app.setDefaultProperties(defProperties);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ManagementApplication.class);
		setDefaultProfile(app);
		app.run(args);
	}
}
