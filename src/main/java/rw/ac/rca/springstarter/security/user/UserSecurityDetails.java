package rw.ac.rca.springstarter.security.user;

import rw.ac.rca.springstarter.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSecurityDetails implements UserDetails {

    private String email;
    private String password;

    public List<GrantedAuthority> grantedAuthorities=new ArrayList<>();

    public UserSecurityDetails(User user){
        this.email=user.getEmail();
        this.password=user.getPassword();
        System.out.println("Roles size: "+user.getRoles().size());
        user.getRoles().forEach(role -> {
            UserAuthority userAuthority= new UserAuthority(user.getId(), role.name());
            grantedAuthorities.add(userAuthority);
        });
    }
    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
