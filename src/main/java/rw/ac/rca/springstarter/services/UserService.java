package rw.ac.rca.springstarter.services;

import rw.ac.rca.springstarter.dto.requests.CreateUserDto;
import rw.ac.rca.springstarter.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String id);
    User saveUser(CreateUserDto createUserDto);
    void deleteUser(String id);
}
