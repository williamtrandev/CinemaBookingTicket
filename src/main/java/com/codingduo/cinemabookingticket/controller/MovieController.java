package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.service.impl.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    @Autowired
    private IMovieService movieService;


    private MovieDTO convertToMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setName(movie.getName());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setActors(movie.getActors());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setComing(movie.isComing());
        movieDTO.setImgPath(movie.getImagePath());
        movieDTO.setTrailerPath(movie.getTrailerPath());
        movieDTO.setTagId(movie.getTagMovie().getId());
        return movieDTO;
    }

    @GetMapping("/getAll")
    public List<MovieDTO> getAllMovie() {
        List<Movie> movieList = movieService.getAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for(Movie movie : movieList ) {
            movieDTOList.add(convertToMovieDTO(movie));
        }
        return movieDTOList;
    }

    @GetMapping("/getOne/{id}")
    public MovieDTO getMovie(@PathVariable("id") Long id) {
        return convertToMovieDTO(movieService.getOne(id));
    }

}
