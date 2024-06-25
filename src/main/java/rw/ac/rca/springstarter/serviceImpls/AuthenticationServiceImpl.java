package rw.ac.rca.springstarter.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.ac.rca.springstarter.dto.requests.LoginDTO;
import rw.ac.rca.springstarter.dto.response.LoginResponse;
import rw.ac.rca.springstarter.enums.EUserStatus;
import rw.ac.rca.springstarter.exceptions.NotFoundException;
import rw.ac.rca.springstarter.model.User;
import rw.ac.rca.springstarter.payload.ApiResponse;
import rw.ac.rca.springstarter.repositories.IUserRepository;
import rw.ac.rca.springstarter.security.jwt.JwtUtils;
import rw.ac.rca.springstarter.security.user.UserSecurityDetails;
import rw.ac.rca.springstarter.security.user.UserSecurityDetailsService;
import rw.ac.rca.springstarter.services.AuthenticationService;
import rw.ac.rca.springstarter.services.IUserService;
import rw.ac.rca.springstarter.utils.ExceptionUtils;
import rw.ac.rca.springstarter.utils.Hash;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private IUserRepository userRepository;
    private UserSecurityDetailsService userSecurityDetailsService;
    private JwtUtils jwtUtils;

    private MailServiceImpl mailService;
    private IUserService userService;




    @Autowired
    public AuthenticationServiceImpl(IUserRepository userRepository, UserSecurityDetailsService userSecurityDetailsService, JwtUtils jwtUtils, MailServiceImpl mailService, IUserService userService) {
        this.userRepository = userRepository;
        this.userSecurityDetailsService = userSecurityDetailsService;
        this.jwtUtils=jwtUtils;

        this.mailService = mailService;
        this.userService = userService;

    }


//    @Transactional
//    private void assignTheRoles(Person person , User user , Role role){
//
//    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginDTO dto) {
        try {
            User user = userRepository.findUserByEmail(dto.getEmail()).orElseThrow(()->{
                throw new NotFoundException("User not found");
            });
                if(Hash.isTheSame(dto.getPassword() , user.getPassword())){
                    if(user.getAccountStatus().equals(EUserStatus.ACTIVE)){
                        // loop in the roles and fetch the type of the user accordingly
                        // then create the token
                        UserSecurityDetails userSecurityDetails = (UserSecurityDetails) userSecurityDetailsService.loadUserByUsername(dto.getEmail());

                        List<GrantedAuthority> grantedAuthorities = userSecurityDetails.getGrantedAuthorities();
                        // if the user has no role
                        String token = jwtUtils.createToken(user.getId(), dto.getEmail() , grantedAuthorities);
                        return ResponseEntity.ok().body(new ApiResponse(true , "Success in login" , new LoginResponse(token , user,user.getRoles())));
                    }else{
                        return ResponseEntity.badRequest().body(new ApiResponse(
                                false,
                                "Verify the email to continue"
                        ));
                    }
                }else{
                    return ResponseEntity.badRequest().body(new ApiResponse(
                            false,
                            "Incorrect Email or Password"
                    ));
                }


        }catch (Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse> verifyAccount(String email, String code) {
        try {
            // find the user by email
            Optional<User> optionalUser = userRepository.findUserByEmail(email);
            // if the user is not found
            if(optionalUser.isEmpty()){
                return ResponseEntity.badRequest().body(new ApiResponse(
                        false,
                        "Email Verification Failed"
                ));
            }else{

                User user = optionalUser.get();
                // if the user has the same activation code
                if(user.getActivationCode().equals(code)){
                    user.setAccountStatus(EUserStatus.ACTIVE);
                    userRepository.save(user);
                    mailService.sendUserRegistrationEmail(optionalUser.get());

                    return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Email Verification Complete"
                    ));
                }else{
                    // if the user has a different activation code
                    return ResponseEntity.badRequest().body(new ApiResponse(
                            false,
                            "Email Verification Failed"
                    ));
                }
            }
        }catch (Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
//    @Override
//    @Transactional
//    public ResponseEntity<ApiResponse> verifyResetCode(String email, String code) {
//        try {
//            Optional<User> optionalUser = userRepository.findUserByEmail(email);
//            if(optionalUser.isEmpty()){
//                return ResponseEntity.badRequest().body(new ApiResponse(
//                        false,
//                        "Code Verification failed"
//                ));
//            }else{
//                User user = optionalUser.get();
//                if(user.getActivationCode().equals(code)){
//                    return ResponseEntity.ok().body(new ApiResponse(
//                            true,
//                            "Code Verification Complete"
//                    ));
//                }else{
//                    return ResponseEntity.badRequest().body(new ApiResponse(
//                            false,
//                            "Wrong Verification Code"
//                    ));
//                }
//            }
//        }catch (Exception e){
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }
//
//
//    @Override
//    @Transactional
//    public ResponseEntity<ApiResponse> resendVerificationCode(String email) {
//        try {
//            Optional<User> optionalUser = userRepository.findUserByEmail(email);
//            if(optionalUser.isEmpty()){
//                return ResponseEntity.badRequest().body(new ApiResponse(
//                        false,
//                        "Failed to resend the verification"
//                ));
//            }else{
//                User user = optionalUser.get();
//                mailService.sendAccountVerificationEmail(user);
//                return ResponseEntity.ok().body(new ApiResponse(
//                        true,
//                        "Successfully Resent the verification",
//                        user
//                ));
//            }
//        }catch (Exception e){
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }

//    @Override
//    @Transactional
//    public ResponseEntity<ApiResponse> resetPassword(ResetPasswordDTO dto) {
//        try {
//            Optional<User> optionalUser = userRepository.findUserByEmail(dto.getEmail());
//            if(optionalUser.isPresent()){
//                User user = optionalUser.get();
//                Hash hash = new Hash();
//                String newPassword = hash.hashPassword(dto.getNewPassword());
//                user.setPassword(newPassword);
//                userRepository.save(user);
//                return ResponseEntity.ok().body(new ApiResponse(
//                        true,
//                        "Password Reset Successfully",
//                        user
//                ));
//            }else{
//                return ResponseEntity.badRequest().body(new ApiResponse(
//                        false,
//                        "Reset Password Failed"
//                ));
//            }
//        }catch (Exception e){
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }



    @Override
    public ResponseEntity<ApiResponse> getProfileById(long id) {
        try {
            User user = userService.getUserById(id);
            if(user == null) {
                return ResponseEntity.status(400).body(new ApiResponse(
                        false,
                        "Invalid User"
                ));
            }
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully Fetched the User Profile",
                    user
            ));
        }catch (Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    public ResponseEntity<ApiResponse> getProfile() {
        try {
            User user = null;
            if(userService.isUserLoggedIn()){
                User loggedInUser = userService.getLoggedInUser();
                if(loggedInUser == null){
                    return ResponseEntity.status(401).body(new ApiResponse(
                            false,
                            "User Not Logged in!!"
                    ));
                }else{
                    user = loggedInUser;
                    return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Successfully Fetched the User Profile",
                            user
                    ));
                }

            }else{
                return ResponseEntity.status(401).body(new ApiResponse(
                        false,
                        "User Not Logged in!!"
                ));
            }
        }catch (Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }


//    @Override
//    @Transactional
//    public ResponseEntity<ApiResponse> initiatePasswordReset(String email) {
//        try {
//            User user = userRepository.findByEmail(email);
//            if(user == null){
//                return ResponseEntity.status(400).body(new ApiResponse(
//                        false,
//                        "Invalid EmailS"
//                ));
//            }else{
//                String newActivationCode = Utility.randomUUID(6 , 0 , 'N');
//                user.setActivationCode(newActivationCode);
//                mailService.sendResetPasswordMail(user);
//                return ResponseEntity.status(200).body(new ApiResponse(
//                        true,
//                        "Successfully initiated password reset",
//                        user
//                ));
//            }
//        }catch (Exception e){
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }

//    public ResponseEntity<ApiResponse> changeProfile(MultipartFile file, UUID userId) throws IOException {
//        try {
//            User user = userRepository.findById(userId).orElseThrow(()->{
//                throw new BadRequestException("User not found");
//            });
//            if(user == null){
//                return ResponseEntity.status(400).body(new ApiResponse(
//                        false,
//                        "Invalid User"
//                ));
//            }else{
//                String profilePic = fileService.uploadFile(file);
//                user.setProfilePicture(profilePic);
//                userRepository.save(user);
//                return ResponseEntity.status(200).body(new ApiResponse(
//                        true,
//                        "Successfully changed the profile picture",
//                        user
//                ));
//            }
//        }catch (Exception e){
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }
}
