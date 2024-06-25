package rw.ac.rca.springstarter.services;

import org.springframework.http.ResponseEntity;
import rw.ac.rca.springstarter.dto.requests.LoginDTO;
import rw.ac.rca.springstarter.payload.ApiResponse;


import java.util.UUID;

public interface AuthenticationService {
    ResponseEntity<ApiResponse> login(LoginDTO dto);
    ResponseEntity<ApiResponse> verifyAccount(String email, String code);


    ResponseEntity<ApiResponse> getProfileById(long id);

    ResponseEntity<ApiResponse> getProfile();



}
