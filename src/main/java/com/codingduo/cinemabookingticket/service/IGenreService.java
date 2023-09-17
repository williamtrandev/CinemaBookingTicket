package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.model.Genre;

import java.util.List;

public interface IGenreService {
    List<Genre> getGenreForShowing();
    List<Genre> getAll();
}
