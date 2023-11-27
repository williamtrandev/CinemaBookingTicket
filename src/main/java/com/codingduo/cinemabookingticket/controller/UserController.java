package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.config.UserSystemDetails;
import com.codingduo.cinemabookingticket.model.History;
import com.codingduo.cinemabookingticket.model.UserSystem;
import com.codingduo.cinemabookingticket.service.IUserSystemService;
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
    private IUserSystemService customerService;

    @GetMapping
    public String userPage(Model model, Principal principal) {
        String email = ((UserSystemDetails) ((Authentication) principal).getPrincipal()).getUsername();
        UserSystem customer = customerService.findByEmail(email);
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
