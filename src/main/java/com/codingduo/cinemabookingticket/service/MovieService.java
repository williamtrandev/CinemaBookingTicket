package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
}
