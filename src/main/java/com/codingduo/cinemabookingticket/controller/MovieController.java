package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.service.impl.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private IMovieService movieService;


    @GetMapping("/showing")
    public String showingMoviePage(Model model) {
        List<MovieDTO> movieList = movieService.getAll();
        List<MovieDTO> showingList = new ArrayList<>();
        for(MovieDTO movie : movieList) {
            if(!movie.isComing()) {
                showingList.add(movie);
            }
        }
        model.addAttribute("title", "Phim đang chiếu");
        model.addAttribute("showingList", showingList);
        return "showing_movie";
    }

    @GetMapping("/coming")
    public String comingMoviePage(Model model) {
        List<MovieDTO> movieList = movieService.getAll();
        List<MovieDTO> comingList = new ArrayList<>();
        for(MovieDTO movie : movieList) {
            if(movie.isComing()) {
                comingList.add(movie);
            }
        }
        model.addAttribute("title", "Phim sắp chiếu");
        model.addAttribute("comingList", comingList);
        return "coming_movie";
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<MovieDTO> getAllMovie(Model model) {
        List<MovieDTO> movieList = movieService.getAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for(MovieDTO movie : movieList ) {
            movieDTOList.add(movie);
        }
        return movieDTOList;
    }

    @GetMapping("/{id}")
    public String getMovie(@PathVariable("id") Long id, Model model) {
        model.addAttribute("movie", movieService.getOne(id));
        model.addAttribute("title", "Chi tiết phim");
        model.addAttribute("links", new String[]{"infofilm.css"});
        return "detail_movie";
    }


}
