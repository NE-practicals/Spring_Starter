package rw.ac.rca.springstarter.dto.requests;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import rw.ac.rca.springstarter.enums.Roles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateUserDto {
    private String name;
    private String password;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid", regexp ="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;
    private Set<Roles> roles;

}




