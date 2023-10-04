package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.service.IMovieService;
import com.codingduo.cinemabookingticket.utils.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieApi {
    @Autowired
    private IMovieService movieService;

    @PostMapping("/save")
    public ResponseEntity<MovieDTO> saveMovie(@ModelAttribute MovieDTO movieDTO,
                                           @RequestParam("image") MultipartFile image,
                                           @RequestParam("genre") String[] genre) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        movieDTO.setImgPath(fileName);
        List<String> genres = Arrays.asList(genre);
        movieDTO.setGenres(genres);
        String uploadDir = "public/movie";
        FileUploadUtil.saveFile(uploadDir, fileName, image);

        return new ResponseEntity<>(movieService.save(movieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable("id") Long id,
                                             @ModelAttribute MovieDTO movieDTO,
                                             @RequestParam("image") MultipartFile image,
                                             @RequestParam("genre") String[] genre) throws IOException {
        if (!image.isEmpty()) {
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            movieDTO.setImgPath(fileName);
            String uploadDir = "public/movie";
            FileUploadUtil.saveFile(uploadDir, fileName, image);
        }
        List<String> genres = Arrays.asList(genre);
        movieDTO.setGenres(genres);
        return new ResponseEntity<>(movieService.update(id, movieDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MovieDTO> deteleMovie(@PathVariable("id") Long id) {
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

    @GetMapping("/getAll/{name}/{coming}")
    public List<MovieDTO> getAllMovieByNameLike(@PathVariable("name") String name, @PathVariable("coming") boolean coming) {
        return movieService.getAllByNameLikeAndComing(name, coming);
    }

    @GetMapping("/getAllShowing")
    public List<MovieDTO> getAllShowing() {
        List<MovieDTO> movieList = movieService.getAll();
        List<MovieDTO> showingList = new ArrayList<>();
        for(MovieDTO movie : movieList) {
            if(!movie.isComing()) {
                showingList.add(movie);
            }
        }
        return showingList;
    }

    @GetMapping("/getAllComing")
    public List<MovieDTO> getAllComing() {
        List<MovieDTO> movieList = movieService.getAll();
        List<MovieDTO> comingList = new ArrayList<>();
        for(MovieDTO movie : movieList) {
            if(movie.isComing()) {
                comingList.add(movie);
            }
        }
        return comingList;
    }

    @GetMapping("/getAllByGenreId/{id}")
    public List<MovieDTO> getAllByGenreId(@PathVariable("id") Long id) {
        return movieService.getAllByGenreId(id);
    }

//    @PostMapping("/create")
//    public MovieDTO createMovieResult(@ModelAttribute MovieDTO movieDTO,
//                                    @RequestParam("image") MultipartFile image,
//                                    @RequestParam("genre") String[] genre) throws IOException {
//        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
//        movieDTO.setImgPath(fileName);
//        List<String> genres = Arrays.asList(genre);
//        movieDTO.setGenres(genres);
//        Movie movieSave = movieService.save(movieDTO);
//        String uploadDir = "public/movie";
//        FileUploadUtil.saveFile(uploadDir, fileName, image);
//        return movieDTO;
//    }

}
