package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.service.impl.IMovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieApi {
    @Autowired
    private IMovieService movieService;

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.save(movieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id,
                                             @Valid @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.update(id, movieDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Movie> deteleMovie(@PathVariable("id") Long id) {
        return new ResponseEntity<>(movieService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<MovieDTO> getAllMovie() {
        return movieService.getAll();
    }

    @GetMapping("/getOne/{id}")
    public MovieDTO getMovie(@PathVariable("id") Long id) {
        return movieService.getOne(id);
    }

    @GetMapping("/getAll/{name}")
    public List<MovieDTO> getAllMovieByNameLike(@PathVariable("name") String name) {
        return movieService.getAllByNameLike(name);
    }

}
