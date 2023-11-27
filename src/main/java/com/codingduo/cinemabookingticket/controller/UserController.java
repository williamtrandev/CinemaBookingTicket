package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.config.CustomerDetails;
import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.Customer;
import com.codingduo.cinemabookingticket.model.History;
import com.codingduo.cinemabookingticket.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String userPage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String email = ((CustomerDetails) ((Authentication) principal).getPrincipal()).getUsername();
        Customer customer = customerService.findByEmail(email);
        List<History> historyList = customer.getHistoryList();
        int ticketNum = 0;
        for (History history: historyList) {
            ticketNum += history.getHistoryDetailList().size();
        }
        customer.setPassword(null);
        model.addAttribute("customer", customer);
        model.addAttribute("histories", historyList);
        model.addAttribute("ticketNum", ticketNum);
        model.addAttribute("title", "Thông tin tài khoản");
        model.addAttribute("links", new String[]{"ttuser.css"});
        return "customer_detail";
    }
}
