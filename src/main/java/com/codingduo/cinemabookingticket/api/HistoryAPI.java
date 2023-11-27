package com.codingduo.cinemabookingticket.api;


import com.codingduo.cinemabookingticket.config.UserSystemDetails;
import com.codingduo.cinemabookingticket.dto.HistoryDTO;
import com.codingduo.cinemabookingticket.model.History;
import com.codingduo.cinemabookingticket.service.IHistoryService;
import com.codingduo.cinemabookingticket.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryAPI {
    @Autowired
    private IMailService mailService;

    @Autowired
    private IHistoryService historyService;

    @PostMapping("/save")
    public ResponseEntity<History> save(@RequestBody HistoryDTO historyDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            UserSystemDetails customer = (UserSystemDetails) authentication.getPrincipal();

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
