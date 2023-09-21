package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.dto.TagMovieDTO;
import com.codingduo.cinemabookingticket.model.Genre;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.service.IGenreService;
import com.codingduo.cinemabookingticket.service.IMovieService;
import com.codingduo.cinemabookingticket.service.ITagMovieService;
import com.codingduo.cinemabookingticket.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        List<Genre> genreList = genreService.getAll();
        model.addAttribute("movieDTO", new MovieDTO());
        model.addAttribute("tagMovieList", tagMovieList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("title", "Thêm phim");
        model.addAttribute("fTitle", "THÊM PHIM");
        return "create_movie";
    }

    @PostMapping("/movie")
    public String createMovieResult(@ModelAttribute MovieDTO movieDTO,
                                    @RequestParam("image") MultipartFile image,
                                    @RequestParam("genre") String[] genre) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        movieDTO.setImgPath(fileName);
//        String uploadDir = "static/img/movie/";
//        FileUploadUtil.saveFile(uploadDir, fileName, image);

        List<String> genres = Arrays.asList(genre);
        movieDTO.setGenres(genres);
        Movie movieSave = movieService.save(movieDTO);

        if(movieSave != null) {
            try{
                File saveFile = new ClassPathResource("static/img/movie").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + image.getOriginalFilename());
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


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
