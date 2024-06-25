package rw.ac.rca.springstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.springstarter.model.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long>{

    Optional<User> findUserByEmail(String email);
}
