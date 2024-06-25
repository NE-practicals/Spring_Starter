package rw.ac.rca.springstarter.dto.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateAccountDto {

    @NotNull(message = "Customer Id cannot be blank")
    private Long customerId;
    @NotNull(message = "Account Type cannot be blank")
    //validate account type to contain only letters

    @NotNull(message = "Balance cannot be blank")
    //validate balance to contain only numbers
    private Long balance;

    @NotNull(message = "Account Number cannot be blank")
    //validate account number to contain only numbers
    @Pattern(regexp = "^[0-9]*$", message = "Account Number should contain only numbers")
     private String accountNumber;

}
