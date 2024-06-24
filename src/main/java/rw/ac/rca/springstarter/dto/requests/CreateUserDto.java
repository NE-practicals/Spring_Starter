package rw.ac.rca.springstarter.dto.requests;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import rw.ac.rca.springstarter.enums.Roles;
import rw.ac.rca.springstarter.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Name cannot be blank")
    //validate name to contain only letters
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name should contain only letters")
    private String name;
    @NotBlank(message = "Password cannot be blank")

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase, one uppercase, one special character and at least 8 characters")
    private String password;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid", regexp ="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;
    private Set<UUID> roles;

}




