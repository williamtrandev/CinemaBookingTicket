package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.UserSystem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserSystemService {

    List<CustomerDTO> getAllCustomer();

    UserSystem findByEmail(String email);

    UserSystem save(CustomerDTO customerDTO);

    CustomerDTO getInfo(Long id);

    UserSystem update(Long id, CustomerDTO customerDTO);

    Page<CustomerDTO> pageCustomer(int pageNum);

    void changePassword(Long id, String hashPassword);
}
