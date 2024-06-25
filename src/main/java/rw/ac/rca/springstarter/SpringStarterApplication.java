package rw.ac.rca.springstarter;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rw.ac.rca.springstarter.enums.Roles;
import rw.ac.rca.springstarter.model.Role;
import rw.ac.rca.springstarter.repositories.IRoleRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class SpringStarterApplication {
	private final IRoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplication.class, args);
	}
	@Bean
	public void registerRoles(){
		Set<Roles> userRoles = new HashSet<>();
		userRoles.add(Roles.ADMIN);
		userRoles.add(Roles.CUSTOMER);
		userRoles.add(Roles.ACCOUNTANT);

		for(Roles role : userRoles ){
			Role sampleRole = new Role(role.toString());
			if(roleRepository.findByRoleName(role.name()) == null){
				roleRepository.save(sampleRole);
			}
		}
	}


}
