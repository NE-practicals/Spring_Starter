package rw.ac.rca.springstarter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.springstarter.dto.requests.CreateAccountDto;
import rw.ac.rca.springstarter.dto.requests.DepositDto;
import rw.ac.rca.springstarter.dto.requests.TransferDto;
import rw.ac.rca.springstarter.dto.requests.WithdrawDto;
import rw.ac.rca.springstarter.payload.ApiResponse;
import rw.ac.rca.springstarter.serviceImpls.AccountServiceImpl;
import rw.ac.rca.springstarter.utils.ExceptionUtils;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers/account")
@Validated
public class AccountController {

   private final AccountServiceImpl accountServiceImpl;

   @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAccounts(){
         try{
              return ResponseEntity.ok(new ApiResponse(true,"Accounts retrieved successfully",accountServiceImpl.getAllAccounts()));
         }catch (Exception e){
              return ExceptionUtils.handleControllerExceptions(e);
         }
    }

   @PostMapping("/create_account")
    public ResponseEntity<ApiResponse> createAccount(@RequestBody @Valid CreateAccountDto createAccountDto){
         try{
              return ResponseEntity.ok(new ApiResponse(true,"Account created successfully",accountServiceImpl.createAccount(createAccountDto)));
         }catch(Exception e){
              return ExceptionUtils.handleControllerExceptions(e);
         }
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> deposit(@RequestBody @Valid DepositDto depositDto){
             try{
                return ResponseEntity.ok(new ApiResponse(true,"Account deposited successfully",accountServiceImpl.deposit(depositDto)));
             }catch(Exception e){
                return ExceptionUtils.handleControllerExceptions(e);
             }
        }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdraw(@RequestBody @Valid WithdrawDto withdrawDto){
             try{
                return ResponseEntity.ok(new ApiResponse(true,"Account withdrawn successfully",accountServiceImpl.withdraw(withdrawDto)));
             }catch(Exception e){
                return ExceptionUtils.handleControllerExceptions(e);
             }
        }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> transfer(@RequestBody @Valid TransferDto transferDto){
             try{
                accountServiceImpl.transfer(transferDto);
                return ResponseEntity.ok(new ApiResponse(true,"Transferred successfully"));
             }catch(Exception e){
                return ExceptionUtils.handleControllerExceptions(e);
             }
        }

    @GetMapping("/get_account_by_customer_id/{customerId}")
    public ResponseEntity<ApiResponse> getAccountByCustomerId(@PathVariable Long customerId){
             try{
                return ResponseEntity.ok(new ApiResponse(true,"Account retrieved successfully",accountServiceImpl.getAccountByCustomerId(customerId)));
             }catch(Exception e){
                return ExceptionUtils.handleControllerExceptions(e);
             }
        }


    @GetMapping("/get_transaction")
    public ResponseEntity<ApiResponse> getAllTransactions(){
             try{
                return ResponseEntity.ok(new ApiResponse(true,"Transactions retrieved successfully",accountServiceImpl.getAllTransactions()));
             }catch(Exception e){
                return ExceptionUtils.handleControllerExceptions(e);
             }
   }
}
