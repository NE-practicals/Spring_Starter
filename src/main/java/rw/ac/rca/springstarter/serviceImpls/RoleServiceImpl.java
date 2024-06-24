package rw.ac.rca.springstarter.serviceImpls;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.springstarter.model.Role;
import rw.ac.rca.springstarter.repositories.RoleRepository;
import rw.ac.rca.springstarter.services.RoleService;
@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role saveRole(String roleName) {
         Role role= new Role(roleName);

        return roleRepository.save(role);

    }
}
