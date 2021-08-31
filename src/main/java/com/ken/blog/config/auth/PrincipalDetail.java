package com.ken.blog.config.auth;

import com.ken.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Spring security intercepts login logic and it's success
// UserDetails type object will be stored in spring security primary session storage
@Getter
public class PrincipalDetail implements UserDetails {
    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //check account is expired or not (true: not expire)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // check account is locked or not (true: not lock)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // check password is expired or not (true: not expire)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // check account is enable or not (true:enable)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // check account's authority (authority could be multiple loops but here's one role)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        /*
        collectors.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_"+user.getRole(); // ROLE_USER
            }
        });
        */

        // lambda
        collectors.add(()->{
            return "ROLE_"+user.getRole(); // ROLE_USER
        });
        return collectors;
    }
}
