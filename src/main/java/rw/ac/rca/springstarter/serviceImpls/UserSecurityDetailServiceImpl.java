package rw.ac.rca.springstarter.serviceImpls;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rw.ac.rca.springstarter.exceptions.NotFoundException;
import rw.ac.rca.springstarter.model.User;
import rw.ac.rca.springstarter.repositories.UserRepository;
import rw.ac.rca.springstarter.security.user.UserSecurityDetails;
import rw.ac.rca.springstarter.utils.ExceptionUtils;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            System.out.println("Username: "+username);
            Optional<User> user=userRepository.findByEmail(username);
            System.out.println("User: "+user.get());
            if(user.isPresent()){
                System.out.println("User found");
                return new UserSecurityDetails(user.get());
            }else{
                System.out.println("User not found");
                throw new NotFoundException("user not found");
            }
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
