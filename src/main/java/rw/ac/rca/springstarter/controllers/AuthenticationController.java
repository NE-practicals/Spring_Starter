package rw.ac.rca.springstarter.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.springstarter.dto.requests.LoginDTO;
import rw.ac.rca.springstarter.payload.ApiResponse;
import rw.ac.rca.springstarter.serviceImpls.AuthenticationServiceImpl;


import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin("*")
@Slf4j
public class AuthenticationController{
    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody() LoginDTO dto){
        log.info("Request for logging in with email : {} and password : {}", dto.getEmail(),dto.getPassword());
        return authenticationService.login(dto);
    }
    @GetMapping("/verify-account")
    public ResponseEntity<ApiResponse> verifyAccount(@RequestParam String email,@RequestParam String code){

        return authenticationService.verifyAccount(email,code);

    }
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile(){
        return authenticationService.getProfile();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ApiResponse> getProfileById(@PathVariable(value="id") long id) {
        return authenticationService.getProfileById(id);
    }

}
