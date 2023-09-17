package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.ShowTime;
import com.codingduo.cinemabookingticket.model.Theater;
import com.codingduo.cinemabookingticket.service.IMovieService;
import com.codingduo.cinemabookingticket.service.IShowTimeService;
import com.codingduo.cinemabookingticket.service.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IShowTimeService showTimeService;

    @Autowired
    private ITheaterService theaterService;

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

    @GetMapping("/{id}")
    public String getMovie(@PathVariable("id") Long id, Model model) {
        MovieDTO movieDTO = movieService.getOne(id);
        List<Theater> theaterList = theaterService.getAll();
        for(Theater theater : theaterList) {
            List<ShowTime> showTimeList = showTimeService.getShowTimeByTheater(theater.getId());
            System.out.println("showtime " + showTimeList.toString());
        }
        String genres = String.join(", ", movieDTO.getGenres());
        model.addAttribute("movie", movieDTO);
        model.addAttribute("genre", genres);
        model.addAttribute("title", "Chi tiết phim");
        model.addAttribute("links", new String[]{"detailMovie.css"});
        model.addAttribute("scripts", new String[]{"handleDetailMovie.js"});
        return "detail_movie";
    }


}
