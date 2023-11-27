package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.UserSystem;
import com.codingduo.cinemabookingticket.service.IUserSystemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private IUserSystemService customerService;



    @GetMapping("/getInfo/{id}")
    public CustomerDTO getInfo(@PathVariable("id") Long id) {
        return customerService.getInfo(id);
    }

//    @PutMapping("/update")
//    public UserSystem updateInfo(@Valid @RequestBody CustomerDTO customerDTO) {
//        return customerService.update(customerDTO);
//    }

}
