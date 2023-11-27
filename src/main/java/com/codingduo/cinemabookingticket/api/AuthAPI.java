package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.api.request.AuthRequest;
import com.codingduo.cinemabookingticket.config.JwtService;
import com.codingduo.cinemabookingticket.config.UserSystemDetails;
import com.codingduo.cinemabookingticket.service.IUserSystemService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthAPI {
    @Autowired
    private IUserSystemService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {

            // Lấy thông tin về vai trò của người dùng
            UserSystemDetails userDetails = (UserSystemDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream().findFirst()
                    .map(GrantedAuthority::getAuthority).orElse("CUSTOMER");;

            // Tạo một đối tượng chứa token và role
            Map<String, Object> responseBody = new HashMap<>();
            String token = jwtService.generateToken(authRequest.getEmail());
            responseBody.put("token", token);
            responseBody.put("role", role);
            // Tạo cookie
            Cookie cookie = new Cookie("jwt", token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60*24);

            response.addCookie(cookie);

            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }
}
