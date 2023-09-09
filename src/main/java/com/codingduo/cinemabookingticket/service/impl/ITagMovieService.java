package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.TagMovieDTO;
import com.codingduo.cinemabookingticket.model.TagMovie;
import com.codingduo.cinemabookingticket.repository.TagMovieRepository;
import com.codingduo.cinemabookingticket.service.TagMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ITagMovieService implements TagMovieService {

    @Autowired
    TagMovieRepository tagMovieRepository;

    private TagMovieDTO convertToDTO(TagMovie tagMovie) {
        return new TagMovieDTO(tagMovie.getId(), tagMovie.getNameTag());
    }

    @Override
    public TagMovieDTO getOne(Long id) {
        return convertToDTO(tagMovieRepository.getReferenceById(id));
    }

    @Override
    public List<TagMovieDTO> getAll() {
        List<TagMovieDTO> tagMovieDTOList = new ArrayList<>();
        List<TagMovie> tagMovieList = tagMovieRepository.findAll();
        for (TagMovie tagMovie: tagMovieList) {
            tagMovieDTOList.add(convertToDTO(tagMovie));
        }
        return tagMovieDTOList;
    }
}
