package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.TagMovieDTO;

import java.util.List;

public interface ITagMovieService {
    TagMovieDTO getOne(Long id);
    List<TagMovieDTO> getAll();
}
