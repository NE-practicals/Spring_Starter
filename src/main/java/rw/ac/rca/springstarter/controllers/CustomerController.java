package rw.ac.rca.springstarter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.springstarter.dto.requests.CreateCustomerDto;
import rw.ac.rca.springstarter.payload.ApiResponse;
import rw.ac.rca.springstarter.serviceImpls.CustomerServiceImpl;
import rw.ac.rca.springstarter.utils.ExceptionUtils;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
@Validated
public class CustomerController {

private  final CustomerServiceImpl customerServiceImpl;




    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCustomers(){

        try{

            return ResponseEntity.ok(new ApiResponse(true,"Customers retrieved successfully",customerServiceImpl.getAllCustomers()));

        }catch (Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PostMapping("/create_customer")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody @Valid CreateCustomerDto createCustomerDto){
        try{
            return ResponseEntity.ok(new ApiResponse(true,"Customer created successfully",customerServiceImpl.createCustomer(createCustomerDto)));
        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
}
