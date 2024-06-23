package rw.ac.rca.springstarter.serviceImpls;

import lombok.RequiredArgsConstructor;
import rw.ac.rca.springstarter.dto.requests.LoginDTO;
import rw.ac.rca.springstarter.dto.requests.ResetPasswordDTO;
import rw.ac.rca.springstarter.dto.response.LoginResponse;
import rw.ac.rca.springstarter.dto.response.ProfileResponseDTO;
import rw.ac.rca.springstarter.exceptions.BadRequestException;
import rw.ac.rca.springstarter.exceptions.NotFoundException;
import rw.ac.rca.springstarter.model.User;
import rw.ac.rca.springstarter.repositories.UserRepository;
import rw.ac.rca.springstarter.security.jwt.JwtUtils;
import rw.ac.rca.springstarter.security.user.UserAuthority;
import rw.ac.rca.springstarter.security.user.UserSecurityDetails;
import rw.ac.rca.springstarter.services.AuthService;
import rw.ac.rca.springstarter.utils.ExceptionUtils;
import rw.ac.rca.springstarter.utils.Hash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserSecurityDetailServiceImpl userSecurityDetailServiceImpl;
    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        try{
            User user= userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new NotFoundException("email not found"));

            if(Hash.isTheSame(loginDTO.getPassword(),user.getPassword())){
                UserSecurityDetails userSecurityDetails= (UserSecurityDetails)userSecurityDetailServiceImpl.loadUserByUsername(loginDTO.getEmail());
                System.out.println(userSecurityDetails.getGrantedAuthorities());
                List<GrantedAuthority> grantedAuthorities= userSecurityDetails.getGrantedAuthorities();
                if(grantedAuthorities.isEmpty()){
                    throw new BadRequestException("Use has no role");
                }
                UserAuthority userAuthority= (UserAuthority) grantedAuthorities.get(0);
                String role = userAuthority.getAuthority();
                String token= jwtUtils.createToken(user.getId(), loginDTO.getEmail(),role);

                return new LoginResponse(token,user,user.getRoles());

            }else {
                System.out.println("Incorrect username and password");
                throw  new BadRequestException("Incorrect username and password");
            }
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public boolean verifyAccount(String email) {
        return false;
    }

    @Override
    public User initiatePasswordReset(String email) {
        return null;
    }

    @Override
    public User resetPassword(ResetPasswordDTO resetPasswordDTO) {
        return null;
    }

    @Override
    public ProfileResponseDTO getUserProfile() {
        return null;
    }
}
