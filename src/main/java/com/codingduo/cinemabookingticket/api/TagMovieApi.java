package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.dto.TagMovieDTO;
import com.codingduo.cinemabookingticket.service.impl.ITagMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag_movie")
public class TagMovieApi {
    @Autowired
    ITagMovieService tagMovieService;

    @GetMapping("/getAll")
    public List<TagMovieDTO> getAllTagMovie() {
        return tagMovieService.getAll();
    }

    @GetMapping("/getOne/{id}")
    public TagMovieDTO getTagMovie(@PathVariable("id") Long id) {
        return tagMovieService.getOne(id);
    }
}
