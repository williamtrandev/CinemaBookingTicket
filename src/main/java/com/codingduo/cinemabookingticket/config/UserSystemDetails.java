package com.codingduo.cinemabookingticket.config;

import com.codingduo.cinemabookingticket.model.UserSystem;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserSystemDetails implements UserDetails {
    private UserSystem userSystem;
    List<SimpleGrantedAuthority> authorities=null;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userSystem.getRole()));
        return authorities;
    }

    public Long getId() {
        return userSystem.getId();
    }
    @Override
    public String getPassword() {
        return userSystem.getPassword();
    }


    @Override
    public String getUsername() {
        return userSystem.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
