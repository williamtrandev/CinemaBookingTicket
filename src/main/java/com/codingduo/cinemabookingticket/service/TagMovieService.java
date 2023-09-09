package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.TagMovieDTO;

import java.util.List;

public interface TagMovieService {
    TagMovieDTO getOne(Long id);
    List<TagMovieDTO> getAll();
}
