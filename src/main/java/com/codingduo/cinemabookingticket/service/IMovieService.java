package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;

import java.util.List;

public interface IMovieService {
    Movie save(MovieDTO movieDTO);
    Movie update(Long id, MovieDTO movieDTO);
    Movie delete(Long id);
    List<MovieDTO> getAll();
    MovieDTO getOne(Long id);
    List<MovieDTO> getAllByNameLikeAndIsComing(String name, boolean isComing);

    List<MovieDTO> getAllByGenreId(Long id);

    List<MovieDTO> getTop6ByIsComingAndIdDesc();

}
