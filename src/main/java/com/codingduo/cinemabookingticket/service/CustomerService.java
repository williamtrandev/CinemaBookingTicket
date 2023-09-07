package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.Customer;

public interface CustomerService {
    Customer findByEmail(String email);
    Customer save(CustomerDTO customerDTO);
}
