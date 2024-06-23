package rw.ac.rca.springstarter.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class JwtUserInfo {
    String userid;
    String email;
    String role;

}
