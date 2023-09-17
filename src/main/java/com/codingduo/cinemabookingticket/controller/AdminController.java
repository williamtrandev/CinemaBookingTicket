package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.dto.TagMovieDTO;
import com.codingduo.cinemabookingticket.service.IMovieService;
import com.codingduo.cinemabookingticket.service.ITagMovieService;
import com.codingduo.cinemabookingticket.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private ITagMovieService tagMovieService;

    @GetMapping("/movie")
    public String admin(Model model) {
        List<MovieDTO> movieList = movieService.getAll();
        model.addAttribute("movieList", movieList);
        model.addAttribute("title", "Quản lý phim");
        return "admin";
    }

    @GetMapping("/movie/create")
    public String createMovie(Model model) {
        List<TagMovieDTO> tagMovieList = tagMovieService.getAll();
        model.addAttribute("movieDTO", new MovieDTO());
        model.addAttribute("tagMovieList", tagMovieList);
        model.addAttribute("title", "Thêm phim");
        model.addAttribute("fTitle", "THÊM PHIM");
        return "create_movie";
    }

    @PostMapping("/movie")
    public String createMovieResult(@ModelAttribute MovieDTO movieDTO,
                                    @RequestParam("image") MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        movieDTO.setImgPath(fileName);
        String uploadDir = "src/main/resources/static/img/movie/";
        FileUploadUtil.saveFile(uploadDir, fileName, image);
        movieService.save(movieDTO);
        return "redirect:/admin/movie";
    }

    @GetMapping("/movie/{id}")
    public String getMovie(@PathVariable("id") Long id, Model model) {
        MovieDTO movieDTO = movieService.getOne(id);
        String genres = String.join(", ", movieDTO.getGenres());
        model.addAttribute("movie", movieDTO);
        model.addAttribute("genre", genres);
        model.addAttribute("title", "Chi tiết phim");
        model.addAttribute("links", new String[]{"detailMovie.css"});
        return "admin_detail_movie";
    }
}
