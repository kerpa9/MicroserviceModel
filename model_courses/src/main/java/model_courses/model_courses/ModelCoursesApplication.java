package model_courses.model_courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ModelCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModelCoursesApplication.class, args);
	}

}
