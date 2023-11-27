package com.codingduo.cinemabookingticket.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserSystemServiceConfig userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Cookie[] cookies = request.getCookies();
            String token = null;
            String username = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("jwt".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }

            if (token != null) {
                try {
                    username = jwtService.extractUsername(token);
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        if (jwtService.validateToken(token, userDetails)) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    }
                } catch (ExpiredJwtException e) {
                    // Token hết hạn, xóa cookie
                    Cookie expiredCookie = new Cookie("jwt", null);
                    expiredCookie.setMaxAge(0);
                    expiredCookie.setPath("/"); // Đảm bảo cùng đường dẫn với cookie cũ
                    response.addCookie(expiredCookie);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {

        }
    }
}
