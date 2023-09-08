package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.Customer;
import com.codingduo.cinemabookingticket.repository.CustomerRepository;
import com.codingduo.cinemabookingticket.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICustomerService implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
        Customer cs = new Customer();
        cs.setEmail(customerDTO.getEmail());
        cs.setName(customerDTO.getName());
        cs.setPassword(customerDTO.getPassword());
        cs.setPoint(0);
        return customerRepository.save(cs);
    }
}
