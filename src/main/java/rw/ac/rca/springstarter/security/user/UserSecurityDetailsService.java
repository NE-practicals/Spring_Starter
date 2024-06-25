package rw.ac.rca.springstarter.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rw.ac.rca.springstarter.model.User;
import rw.ac.rca.springstarter.repositories.IUserRepository;


import java.util.Optional;

@Component
public class UserSecurityDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Autowired
    public UserSecurityDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isPresent()){
            return new UserSecurityDetails(userOptional.get());
        }else{
            throw new UsernameNotFoundException(email + " was not found");
        }
    }
}
