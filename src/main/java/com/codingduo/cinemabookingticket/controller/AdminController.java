package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.config.UserSystemDetails;
import com.codingduo.cinemabookingticket.dto.ComboDTO;
import com.codingduo.cinemabookingticket.dto.CustomerDTO;
import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.dto.TagMovieDTO;
import com.codingduo.cinemabookingticket.model.*;
import com.codingduo.cinemabookingticket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private ITagMovieService tagMovieService;

    @Autowired
    private IGenreService genreService;

    @Autowired
    private IUserSystemService customerService;

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IComboService comboService;

    @Autowired
    private IShowTimeService showTimeService;

    @Autowired
    private IRoomService roomService;

    @GetMapping("/movie")
    public String admin(Model model) {
        List<MovieDTO> movieList = movieService.getAllNotDeleted();
        List<TagMovieDTO> tagMovieList = tagMovieService.getAll();
        List<Genre> genreList = genreService.getAll();
        model.addAttribute("movie", new MovieDTO());
        model.addAttribute("tagMovieList", tagMovieList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("movieList", movieList);
        model.addAttribute("title", "Quản lý phim");
        model.addAttribute("imageRequired", "true");
        model.addAttribute("btn", "Thêm");
        model.addAttribute("scripts", new String[]{"addMovie.js"});
        return "admin";
    }

    @GetMapping("/deleted-movie")
    public String deletedMovie(Model model) {
        List<MovieDTO> movieList = movieService.getAllDeleted();
        List<TagMovieDTO> tagMovieList = tagMovieService.getAll();
        List<Genre> genreList = genreService.getAll();
        model.addAttribute("movie", new MovieDTO());
        model.addAttribute("tagMovieList", tagMovieList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("movieList", movieList);
        model.addAttribute("title", "Quản lý phim");
//        model.addAttribute("imageRequired", "true");
//        model.addAttribute("btn", "Thêm");
//        model.addAttribute("scripts", new String[]{"addMovie.js"});
        return "admin_movie_deleted";
    }

//    @GetMapping("/movie/create")
//    public String createMovie(Model model) {
//        List<TagMovieDTO> tagMovieList = tagMovieService.getAll();
//        List<Genre> genreList = genreService.getAll();
//        model.addAttribute("movieDTO", new MovieDTO());
//        model.addAttribute("tagMovieList", tagMovieList);
//        model.addAttribute("genreList", genreList);
//        model.addAttribute("title", "Thêm phim");
//        model.addAttribute("fTitle", "THÊM PHIM");
//        return "create_movie";
//    }

//    @PostMapping("/movie")
//    public String createMovie(@ModelAttribute("movie") MovieDTO movie,
//                              @RequestParam("image") MultipartFile image,
//                              @RequestParam("genre") String[] genre) throws IOException {
//
//        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
//        movie.setImgPath(fileName);
//        List<String> genres = Arrays.asList(genre);
//        movie.setGenres(genres);
//        movieService.save(movie);
//        String uploadDir = "public/movie";
//        FileUploadUtil.saveFile(uploadDir, fileName, image);
//
//        return "redirect:/admin/movie";
//    }

    @GetMapping("/movie/{id}")
    public String getMovie(@PathVariable("id") Long id, Model model) {
        MovieDTO movieDTO = movieService.getOne(id);
        List<TagMovieDTO> tagMovieList = tagMovieService.getAll();
        List<Genre> genreList = genreService.getAll();
        String genres = String.join(", ", movieDTO.getGenres());
        List<ShowTime> showTimeList = showTimeService.getShowTimeByMovie(id);
        List<Room> roomList = roomService.getAll();
        List<Ticket> ticketList = ticketService.getAll();
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("roomList", roomList);
        model.addAttribute("showTimeList", showTimeList);
        model.addAttribute("movie", movieDTO);
        model.addAttribute("genre", genres);
        model.addAttribute("tagMovieList", tagMovieList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("title", "Chi tiết phim");
        model.addAttribute("links", new String[]{"detailMovie.css", "ttuser.css"});
//        model.addAttribute("formAction", "/admin/movie/"+id);
//        model.addAttribute("formMethod", "put");
        model.addAttribute("imageRequired", "false");
        model.addAttribute("btn", "Sửa");
        model.addAttribute("scripts", new String[]{"updateMovie.js"});
        return "admin_detail_movie";
    }

//    @PutMapping("/movie/{id}")
//    public String updateMovie(@PathVariable("id") Long id, @ModelAttribute MovieDTO movieDTO,
//                              @RequestParam("image") MultipartFile image,
//                              @RequestParam("genre") String[] genre) throws IOException {
//
//        if (!image.isEmpty()) {
//            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
//            movieDTO.setImgPath(fileName);
//            String uploadDir = "public/movie";
//            FileUploadUtil.saveFile(uploadDir, fileName, image);
//        }
//        List<String> genres = Arrays.asList(genre);
//        movieDTO.setGenres(genres);
//        movieService.update(id, movieDTO);
//
//        return "redirect:/admin/movie/"+id;
//    }

    @DeleteMapping("/movie/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieService.delete(id);
        return "redirect:/admin/movie";
    }

    @GetMapping("/customer")
    public String customersPage(Model model) {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomer();
        for (CustomerDTO customerDTO: customerDTOList) {
            customerDTO.setPassword(null);
        }
        model.addAttribute("customers", customerDTOList);
        model.addAttribute("title", "Danh sách khách hàng");
        return "admin_customers";
    }

    @GetMapping("/customer/{id}")
    public String customerDetailPage(@PathVariable("id") Long id, Model model) {
        CustomerDTO customerDTO = customerService.getInfo(id);
        List<History> histories = historyService.getAllByCustomer(id);
        model.addAttribute("customer", customerDTO);
        model.addAttribute("histories", histories);
        model.addAttribute("title", "Chi tiết khách hàng");
        return "admin_customer_detail";
    }

    @GetMapping("/ticket")
    public String ticketPage(Model model) {
        List<Ticket> ticketList = ticketService.getAll();
        model.addAttribute("tickets", ticketList);
        model.addAttribute("title", "Quản lý vé");
        model.addAttribute("links", new String[]{"ttuser.css"});
        return "admin_ticket";
    }

    @GetMapping("/combo")
    public String comboPage(Model model) {
        List<Combo> combos = comboService.getAllByDeleted(false);
        model.addAttribute("combo", new ComboDTO());
        model.addAttribute("combos", combos);
        model.addAttribute("title", "Danh sách combo");
        model.addAttribute("links", new String[]{"ttuser.css", "booking.css"});
        return "admin_combo";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        String email = ((UserSystemDetails) ((Authentication) principal).getPrincipal()).getUsername();
        UserSystem customer = customerService.findByEmail(email);
        List<History> historyList = customer.getHistoryList();
        customer.setPassword(null);
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Thông tin tài khoản");
        model.addAttribute("links", new String[]{"ttuser.css"});
        return "admin_detail";
    }


    @GetMapping("/income")
    public String income(Model model) {
        Map<Date, Double> revenuesInWeek = historyService.calculateDailyRevenueInWeek();
        model.addAttribute("revenuesInWeek", revenuesInWeek);

        Map<YearMonth, Double> revenuesInMonth = historyService.calculateMonthlyRevenue();
        model.addAttribute("revenuesInMonth", revenuesInMonth);

        model.addAttribute("links", new String[]{"detailMovie.css", "style.css"});
        model.addAttribute("title", "Thống kê doanh thu");
        return "income";
    }
}
