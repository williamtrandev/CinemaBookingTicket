package com.codingduo.cinemabookingticket.config;

import com.codingduo.cinemabookingticket.model.UserSystem;
import com.codingduo.cinemabookingticket.repository.UserSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSystemServiceConfig implements UserDetailsService {
    @Autowired
    private UserSystemRepository userSystemRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        UserSystem userSystem = userSystemRepository.findByEmail(username);
        if(userSystem == null){
            throw new UsernameNotFoundException("Could not find email");
        }
        authorities.add(new SimpleGrantedAuthority(userSystem.getRole()));
        UserSystemDetails userSystemDetails = new UserSystemDetails();
        userSystemDetails.setUserSystem(userSystem);
        userSystemDetails.setAuthorities(authorities);

        return userSystemDetails;
    }
}
