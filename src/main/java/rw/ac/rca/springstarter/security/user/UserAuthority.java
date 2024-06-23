package rw.ac.rca.springstarter.security.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

public class UserAuthority implements GrantedAuthority {
    private UUID user;
    private String authority;

    public UserAuthority(UUID user, String authority){
        this.user=user;
        this.authority=authority;
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }

}
