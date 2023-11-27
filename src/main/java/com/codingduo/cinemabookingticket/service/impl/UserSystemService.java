package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.UserSystem;
import com.codingduo.cinemabookingticket.repository.UserSystemRepository;
import com.codingduo.cinemabookingticket.service.IUserSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSystemService implements IUserSystemService {
    @Autowired
    private UserSystemRepository userSystemRepository;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<UserSystem> customers = userSystemRepository.getAllByRole("CUSTOMER");
        for (UserSystem customer: customers) {
            customerDTOList.add(convertToCustomerDTO(customer));
        }
        return customerDTOList;
    }

    @Override
    public UserSystem findByEmail(String email) {
        return userSystemRepository.findByEmail(email);
    }

    @Override
    public UserSystem save(CustomerDTO customerDTO) {
        UserSystem cs = new UserSystem();
        cs.setEmail(customerDTO.getEmail());
        cs.setName(customerDTO.getName());
        cs.setPassword(customerDTO.getPassword());
        cs.setPoint(0);
        return userSystemRepository.save(cs);
    }

    @Override
    public CustomerDTO getInfo(Long id) {
        return convertToCustomerDTO(userSystemRepository.getReferenceById(id));
    }

    @Override
    public UserSystem update(Long id, CustomerDTO customerDTO) {
        UserSystem customerToUpdate = userSystemRepository.getReferenceById(customerDTO.getId());
        customerToUpdate.setName(customerDTO.getName());
        customerToUpdate.setAvatarPath(customerDTO.getAvatarPath());
        return userSystemRepository.save(customerToUpdate);
    }

    @Override
    public Page<CustomerDTO> pageCustomer(int pageNum) {
        return null;
    }

    @Override
    public void changePassword(Long id, String hashPassword) {

    }

    private CustomerDTO convertToCustomerDTO(UserSystem customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAvatarPath(customer.getAvatarPath());
        customerDTO.setPoint(customer.getPoint());
        return customerDTO;
    }
}
