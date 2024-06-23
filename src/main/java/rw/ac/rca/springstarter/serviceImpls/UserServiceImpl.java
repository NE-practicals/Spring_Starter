package rw.ac.rca.springstarter.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.springstarter.dto.requests.CreateUserDto;
import rw.ac.rca.springstarter.exceptions.BadRequestException;
import rw.ac.rca.springstarter.model.User;
import rw.ac.rca.springstarter.repositories.UserRepository;
import rw.ac.rca.springstarter.services.UserService;
import rw.ac.rca.springstarter.utils.ExceptionUtils;
import rw.ac.rca.springstarter.utils.Hash;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        try{
            return userRepository.findAll();
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public User saveUser(CreateUserDto createUserDto) {
        try {
            Optional<User> user = userRepository.findByEmail(createUserDto.getEmail());

            if (user.isPresent()) {
                throw new BadRequestException("Email already exist");
            }

            User user1 = new User();
            user1.setName(createUserDto.getName());
            user1.setEmail(createUserDto.getEmail());
            user1.setPassword(Hash.hashPassword(createUserDto.getPassword()));
            user1.setRoles(createUserDto.getRoles());
            return userRepository.save(user1);

        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }

    }



    @Override
    public void deleteUser(String id) {


    }
}
