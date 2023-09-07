package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.Customer;
import com.codingduo.cinemabookingticket.service.CustomerService;
import com.codingduo.cinemabookingticket.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin_login";
    }
    @GetMapping("/login")
    public String customerLoginPage(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "customer_login";
    }

    @GetMapping("/home")
    public String home() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
//            return "redirect:/login";
//        }
        return "home";
    }

    @GetMapping("/register")
    public String registerCustomerPage(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "customer_register";
    }
    @PostMapping("/register")
    public String registerCustomer(@Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO,
                                   BindingResult bindingResult,
                                   Model model) {
        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("customerDTO", customerDTO);
                bindingResult.toString();
                System.out.println(bindingResult);
                return "customer_register";
            }
            String email = customerDTO.getEmail();
            Customer customer = customerService.findByEmail(email);
            System.out.println("Email: " + email);
            if(customer == null) {
                if(!customerDTO.getPassword().equals(customerDTO.getRepeatPassword())) {
                    System.out.println("Customer not null");
                    model.addAttribute("nSameErr", "Your password and repeat password is not same");
                    return "customer_register";
                }
                System.out.println("Succ");
                model.addAttribute("signUp", "signUpMode");
                model.addAttribute("succ", "Successfully");
                customerDTO.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));
                customerService.save(customerDTO);
            } else {
                model.addAttribute("dupplicateErr", "Email is used");
                System.out.println("Dup email:" + customer.getEmail());
                return "customer_register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("serverErr", "Can not register because error server");
        }
        return "customer_register";
    }

}
