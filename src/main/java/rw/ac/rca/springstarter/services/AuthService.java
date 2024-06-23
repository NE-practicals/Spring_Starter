package rw.ac.rca.springstarter.services;

import rw.ac.rca.springstarter.dto.requests.LoginDTO;
import rw.ac.rca.springstarter.dto.requests.ResetPasswordDTO;
import rw.ac.rca.springstarter.dto.response.LoginResponse;
import rw.ac.rca.springstarter.dto.response.ProfileResponseDTO;
import rw.ac.rca.springstarter.model.User;

public interface AuthService {

    LoginResponse login(LoginDTO loginDTO);
    boolean verifyAccount(String email);
    User initiatePasswordReset(String email);
    User resetPassword(ResetPasswordDTO resetPasswordDTO);
    ProfileResponseDTO getUserProfile();


}
