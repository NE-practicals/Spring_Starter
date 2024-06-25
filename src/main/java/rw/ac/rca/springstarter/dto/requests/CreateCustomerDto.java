package rw.ac.rca.springstarter.dto.requests;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import rw.ac.rca.springstarter.model.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
public class CreateCustomerDto {
    @NotBlank(message = "Name cannot be blank")
    //validate name to contain only letters
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name should contain only letters")
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name should contain only letters")
   @NotBlank(message = "Name cannot be blank")
    private String lastName;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid", regexp ="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;

    @NotBlank(message = "Mobile cannot be blank")
    @Pattern(regexp = "^[0-9]*$", message = "Mobile should contain only numbers")
    private String mobile;

    @NotNull(message = "Date of birth cannot be blank")
    private Date dob;



}




