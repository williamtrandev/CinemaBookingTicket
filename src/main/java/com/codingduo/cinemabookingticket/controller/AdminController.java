package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.dto.TagMovieDTO;
import com.codingduo.cinemabookingticket.model.*;
import com.codingduo.cinemabookingticket.service.*;
import com.codingduo.cinemabookingticket.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

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
    private ITicketService ticketService;

    @Autowired
    private IComboService comboService;

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
        model.addAttribute("movie", movieDTO);
        model.addAttribute("genre", genres);
        model.addAttribute("tagMovieList", tagMovieList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("title", "Chi tiết phim");
        model.addAttribute("links", new String[]{"detailMovie.css"});
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

    @GetMapping("/customer/getAll")
    public String allCustomer() {
        return "customer";
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
}
