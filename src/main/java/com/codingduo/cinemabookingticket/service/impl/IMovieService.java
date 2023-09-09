package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.repository.MovieRepository;
import com.codingduo.cinemabookingticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class IMovieService implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieDTO> getAll() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOs = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDTO movieDTO = convertToMovieDTO(movie);
            movieDTOs.add(movieDTO);
        }

        return movieDTOs;
    }

    @Override
    public MovieDTO getOne(Long id) {
        return convertToMovieDTO(movieRepository.getReferenceById(id));
    }

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
}
