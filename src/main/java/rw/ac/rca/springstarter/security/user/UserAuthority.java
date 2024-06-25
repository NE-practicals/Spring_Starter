package rw.ac.rca.springstarter.security.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

public class UserAuthority implements GrantedAuthority {

    public long userId;
    public String authority;

    public UserAuthority(long userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
