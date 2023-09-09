package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.repository.MovieRepository;
import com.codingduo.cinemabookingticket.repository.TagMovieRepository;
import com.codingduo.cinemabookingticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IMovieService implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TagMovieRepository tagMovieRepository;

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


    private Movie convertToMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setName(movieDTO.getName());
        movie.setDirector(movieDTO.getDirector());
        movie.setActors(movieDTO.getActors());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setDescription(movieDTO.getDescription());
        movie.setComing(movieDTO.isComing());
        movie.setImagePath(movieDTO.getImgPath());
        movie.setTrailerPath(movieDTO.getTrailerPath());
        movie.setTagMovie(tagMovieRepository.getReferenceById(movieDTO.getTagId()));
        return movie;
    }

    @Override
    public Movie save(MovieDTO movieDTO) {
        return movieRepository.save(convertToMovie(movieDTO));
    }

    @Override
    public Movie update(Long id, MovieDTO movieDTO) {
        Movie existingMovie = movieRepository.getReferenceById(id);
        existingMovie.setName(movieDTO.getName());
        existingMovie.setDirector(movieDTO.getDirector());
        existingMovie.setActors(movieDTO.getActors());
        existingMovie.setReleaseDate(movieDTO.getReleaseDate());
        existingMovie.setDuration(movieDTO.getDuration());
        existingMovie.setDescription(movieDTO.getDescription());
        existingMovie.setComing(movieDTO.isComing());
        existingMovie.setImagePath(movieDTO.getImgPath());
        existingMovie.setTrailerPath(movieDTO.getTrailerPath());
        existingMovie.setTagMovie(tagMovieRepository.getReferenceById(movieDTO.getTagId()));
        return movieRepository.save(existingMovie);
    }

    @Override
    public Movie delete(Long id) {
        Movie existingMovie = movieRepository.getReferenceById(id);
        existingMovie.setDeleted(true);
        return movieRepository.save(existingMovie);
    }



    public List<MovieDTO> getAllByNameLike(String name) {
        List<Movie> movieList = movieRepository.findAllByNameLike("%"+name+"%");
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for(Movie movie : movieList ) {
            movieDTOList.add(convertToMovieDTO(movie));
        }
        return movieDTOList;
    }
}
