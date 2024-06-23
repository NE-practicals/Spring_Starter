package rw.ac.rca.springstarter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.springstarter.dto.requests.CreateUserDto;
import rw.ac.rca.springstarter.payload.ApiResponse;
import rw.ac.rca.springstarter.serviceImpls.UserServiceImpl;
import rw.ac.rca.springstarter.utils.ExceptionUtils;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
@Validated
public class UserController {

    private final UserServiceImpl userServiceImpl;




    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers(){
        try{
            return ResponseEntity.ok(new ApiResponse(true,"Users retrieved successfully",userServiceImpl.getAllUsers()));
        }catch (Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PostMapping("/create_user")
    public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid CreateUserDto createUserDto){
        try{
            return ResponseEntity.ok(new ApiResponse(true,"User created successfully",userServiceImpl.saveUser(createUserDto)));
        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
}
