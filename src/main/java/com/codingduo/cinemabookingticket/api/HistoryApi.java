package com.codingduo.cinemabookingticket.api;


import com.codingduo.cinemabookingticket.config.CustomerDetails;
import com.codingduo.cinemabookingticket.dto.HistoryDTO;
import com.codingduo.cinemabookingticket.model.Customer;
import com.codingduo.cinemabookingticket.model.History;
import com.codingduo.cinemabookingticket.service.IHistoryDetailService;
import com.codingduo.cinemabookingticket.service.IHistoryService;
import com.codingduo.cinemabookingticket.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryApi {
    @Autowired
    private IMailService mailService;

    @Autowired
    private IHistoryService historyService;

    @PostMapping("/save")
    public ResponseEntity<History> save(@RequestBody HistoryDTO historyDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            CustomerDetails customer = (CustomerDetails) authentication.getPrincipal();

            historyDTO.setIdCustomer(customer.getId());
            historyService.save(historyDTO);

            String numSeat = historyDTO.getSeatIds().size() + "";
            String infoSeats = historyDTO.getInfoSeats();
            String infoCombo = historyDTO.getInfoCombo();


            // Gửi mail về cho user
            mailService.sendHtmlEmail(numSeat, infoSeats, infoCombo, customer.getUsername());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
