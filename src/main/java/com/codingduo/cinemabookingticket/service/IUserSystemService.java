package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.UserSystem;
import org.springframework.data.domain.Page;

public interface IUserSystemService {
    UserSystem findByEmail(String email);

    UserSystem save(CustomerDTO customerDTO);

    CustomerDTO getInfo(Long id);

    UserSystem update(CustomerDTO customerDTO);

    Page<CustomerDTO> pageCustomer(int pageNum);
}
