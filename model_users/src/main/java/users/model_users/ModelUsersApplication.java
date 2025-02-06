package users.model_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ModelUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModelUsersApplication.class, args);
	}

}
