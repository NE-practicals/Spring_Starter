package rw.ac.rca.springstarter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import rw.ac.rca.springstarter.enums.Roles;
import rw.ac.rca.springstarter.model.Role;
import rw.ac.rca.springstarter.model.User;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

   public String token;
    public User userData;
    public Set<Role> userRole;


}
