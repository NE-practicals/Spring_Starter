package rw.ac.rca.springstarter.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import rw.ac.rca.springstarter.model.Role;


import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class JwtUserInfo {
    private Integer userId;
    private String email;
    private List<Role> role;
}

