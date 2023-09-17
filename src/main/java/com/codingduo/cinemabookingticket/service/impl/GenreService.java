package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.model.Genre;
import com.codingduo.cinemabookingticket.repository.GenreRepository;
import com.codingduo.cinemabookingticket.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements IGenreService {

    @Autowired
    GenreRepository genreRepository;


    @Override
    public List<Genre> getGenreForShowing() {
        return genreRepository.findGenreNamesForShowingMovies();
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
