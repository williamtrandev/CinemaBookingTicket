package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getAll();

    Customer findByEmail(String email);

    Customer save(CustomerDTO customerDTO);

    CustomerDTO getInfo(Long id);

    Customer update(CustomerDTO customerDTO);
}
