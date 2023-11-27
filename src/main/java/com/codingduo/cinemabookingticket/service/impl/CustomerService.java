package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.Customer;
import com.codingduo.cinemabookingticket.repository.CustomerRepository;
import com.codingduo.cinemabookingticket.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer: customers) {
            customerDTOList.add(convertToCustomerDTO(customer));
        }
        return customerDTOList;
    }

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

    @Override
    public CustomerDTO getInfo(Long id) {
        return convertToCustomerDTO(customerRepository.getReferenceById(id));
    }

    @Override
    public Customer update(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
        Customer customerToUpdate = customerRepository.getReferenceById(id);
        if (customer != null && !customer.equals(customerToUpdate)) {
            return null;
        }
        customerToUpdate.setName(customerDTO.getName());
        customerToUpdate.setEmail(customerDTO.getEmail());
        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void changePassword(Long id, String password) {
        Customer customer = customerRepository.getReferenceById(id);
        customer.setPassword(password);
        customerRepository.save(customer);
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAvatarPath(customer.getAvatarPath());
        customerDTO.setPoint(customer.getPoint());
        return customerDTO;
    }
}
