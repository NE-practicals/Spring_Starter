package rw.ac.rca.springstarter;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class SpringStarterApplication {



	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplication.class, args);
	}


}
