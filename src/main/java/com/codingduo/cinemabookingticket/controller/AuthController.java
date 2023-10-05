package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Banner;
import com.codingduo.cinemabookingticket.model.Customer;
import com.codingduo.cinemabookingticket.model.Genre;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.service.IBannerService;
import com.codingduo.cinemabookingticket.service.ICustomerService;
import com.codingduo.cinemabookingticket.service.IGenreService;
import com.codingduo.cinemabookingticket.service.IMovieService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    IMovieService movieService;

    @Autowired
    IBannerService bannerService;

    @Autowired
    IGenreService genreService;

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

    @GetMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/login";
        }
        List<Banner> bannerList = bannerService.getAll();
        List<MovieDTO> comingList = movieService.getTop6ByComingAndIdDesc();
        List<MovieDTO> movies = movieService.getAll();
        List<MovieDTO> movieList = new ArrayList<>();
        for(MovieDTO movie : movies) {
            if(!movie.isComing()) {
                movieList.add(movie);
            }
        }
        List<Genre> genreList = genreService.getGenreForShowing();
        System.out.println(genreList.toString());
        model.addAttribute("comingList", comingList);
        model.addAttribute("movieList", movieList);
        model.addAttribute("banners", bannerList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("links", new String[]{"slider.css"});
        model.addAttribute("title", "Trang chủ");
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
