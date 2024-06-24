package rw.ac.rca.springstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.springstarter.model.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
