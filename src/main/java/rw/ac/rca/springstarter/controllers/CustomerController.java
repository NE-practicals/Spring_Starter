package rw.ac.rca.springstarter.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.springstarter.dto.requests.CreateCustomerDTO;
import rw.ac.rca.springstarter.payload.ApiResponse;
import rw.ac.rca.springstarter.services.ICustomerService;
import rw.ac.rca.springstarter.utils.ExceptionUtils;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private  final ICustomerService customerService;

    private Pageable pageable = null;


    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        try {
//            logAction(String.format("Request for creating a student with Full name :  %s , and email  :  %s", createCustomerDTO.getFirstName() + createCustomerDTO.getLastName() , createCustomerDTO.getEmail()));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Customer created successfully",
                            customerService.createCustomer(createCustomerDTO)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }
    @GetMapping ("/all")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> getAllCustomers() {
        try {
//            logAction(String.format("Request for creating a student with Full name :  %s , and email  :  %s", createCustomerDTO.getFirstName() + createCustomerDTO.getLastName() , createCustomerDTO.getEmail()));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Customer created successfully",
                            customerService.getAllCustomers()
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }


}

