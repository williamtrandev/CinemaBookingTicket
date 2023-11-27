package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMovieService {
    MovieDTO save(MovieDTO movieDTO);
    MovieDTO update(Long id, MovieDTO movieDTO);
    MovieDTO delete(Long id);
//    MovieDTO forceDelete(Long id);
    List<MovieDTO> getAll();
    MovieDTO getOne(Long id);
    List<MovieDTO> getAllNotDeleted();
    List<MovieDTO> getAllDeleted();
    List<MovieDTO> getAllByNameLikeAndComing(String name, boolean coming);

    List<MovieDTO> getAllByGenreId(Long id);

    List<MovieDTO> getTop6ByComingAndIdDesc();

    MovieDTO restore(Long id);
}
