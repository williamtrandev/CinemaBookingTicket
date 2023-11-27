package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.config.UserSystemDetails;
import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.model.UserSystem;
import com.codingduo.cinemabookingticket.service.IUserSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerApi {
    @Autowired
    private IUserSystemService customerService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(
            @PathVariable("id") Long id,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            Principal principal
    ) {
        String email = ((UserSystemDetails) ((Authentication) principal).getPrincipal()).getUsername();
        UserSystem customer = customerService.findByEmail(email);
        boolean isMatch = bCryptPasswordEncoder.matches(oldPassword, customer.getPassword());
        if (!isMatch) {
            return new ResponseEntity<>("Mật khẩu cũ không đúng", HttpStatus.BAD_REQUEST);
        }
        String hashPassword = bCryptPasswordEncoder.encode(newPassword);
        customerService.changePassword(id, hashPassword);
        return new ResponseEntity<>("Đổi mật khẩu thành công", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInfo(
            @PathVariable("id") Long id,
            @ModelAttribute CustomerDTO customerDTO
    ) {
        UserSystem updatedCustomer = customerService.update(id, customerDTO);
        if (updatedCustomer == null) {
            return new ResponseEntity<>("Email đã được sử dụng", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cập nhật thông tin thành công", HttpStatus.OK);
    }
}
