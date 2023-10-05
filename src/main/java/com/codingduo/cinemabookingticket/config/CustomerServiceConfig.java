package com.codingduo.cinemabookingticket.config;

import com.codingduo.cinemabookingticket.model.Customer;
import com.codingduo.cinemabookingticket.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceConfig implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
        Customer customer = customerRepository.findByEmail(username);
        if(customer == null){
            throw new UsernameNotFoundException("Could not find email");
        }
        CustomerDetails customUserDetail = new CustomerDetails();
        customUserDetail.setUser(customer);
        customUserDetail.setAuthorities(authorities);

        return customUserDetail;
    }
}
