package rw.ac.rca.springstarter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rw.ac.rca.springstarter.serviceImpls.RoleServiceImpl;
import rw.ac.rca.springstarter.services.RoleService;

@SpringBootApplication
@AllArgsConstructor
public class SpringStarterApplication {

	private final RoleServiceImpl roleService;


	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplication.class, args);
	}
	@Bean
	public void insertRoles(){

		roleService.saveRole("ADMIN");
		roleService.saveRole("USER");
	}

}
