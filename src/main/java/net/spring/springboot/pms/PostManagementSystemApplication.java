package net.spring.springboot.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostManagementSystemApplication.class, args);
		System.out.println("Server is running at port 5000");
	}

}
