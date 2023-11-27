package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Genre;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.repository.GenreRepository;
import com.codingduo.cinemabookingticket.repository.MovieRepository;
import com.codingduo.cinemabookingticket.repository.TagMovieRepository;
import com.codingduo.cinemabookingticket.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TagMovieRepository tagMovieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<MovieDTO> getAll() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOs = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDTO movieDTO = convertToMovieDTO(movie);
            movieDTOs.add(movieDTO);
        }

        return movieDTOs;
    }

    @Override
    public MovieDTO getOne(Long id) {
        Movie movie = movieRepository.findMovieWithGenresBy(id);
        if(movie == null) return null;
        return convertToMovieDTO(movie);
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.getReferenceById(id);
    }

    @Override
    public List<MovieDTO> getAllNotDeleted() {
        List<Movie> movies = movieRepository.findAllByDeletedOrderByIdDesc(false);
        List<MovieDTO> movieDTOs = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDTO movieDTO = convertToMovieDTO(movie);
            movieDTOs.add(movieDTO);
        }

        return movieDTOs;
    }

    @Override
    public List<MovieDTO> getAllDeleted() {
        List<Movie> movies = movieRepository.findAllByDeletedOrderByIdDesc(true);
        List<MovieDTO> movieDTOs = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDTO movieDTO = convertToMovieDTO(movie);
            movieDTOs.add(movieDTO);
        }

        return movieDTOs;
    }

    private MovieDTO convertToMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setName(movie.getName());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setActors(movie.getActors());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setComing(movie.isComing());
        movieDTO.setImgPath(movie.getImagePath());
        movieDTO.setImgPathAtLocal(movie.getImagePathAtLocal());
        movieDTO.setTrailerPath(movie.getTrailerPath());
        movieDTO.setTagId(movie.getTagMovie().getId());
        // Lấy ds thể loại
        List<String> genreNames = new ArrayList<>();
        for (Genre genre : movie.getGenres()) {
            genreNames.add(genre.getName());
        }
        movieDTO.setGenres(genreNames);
        return movieDTO;
    }

    private Movie convertToMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setName(movieDTO.getName());
        movie.setDirector(movieDTO.getDirector());
        movie.setActors(movieDTO.getActors());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setDescription(movieDTO.getDescription());
        movie.setComing(movieDTO.isComing());
        movie.setImagePath(movieDTO.getImgPath());
        movie.setTrailerPath(movieDTO.getTrailerPath());
        movie.setTagMovie(tagMovieRepository.getReferenceById(movieDTO.getTagId()));
        // Lấy model thể loại
        List<Genre> genres = new ArrayList<>();
        for (String genreName: movieDTO.getGenres()) {
            Genre genre = genreRepository.findByName(genreName);
            genres.add(genre);
        }
        movie.setGenres(genres);
        return movie;
    }

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        Movie movie = movieRepository.save(convertToMovie(movieDTO));
        return convertToMovieDTO(movie);
    }

    @Override
    public MovieDTO update(Long id, MovieDTO movieDTO) {
        Movie existingMovie = movieRepository.getReferenceById(id);
        existingMovie.setName(movieDTO.getName());
        existingMovie.setDirector(movieDTO.getDirector());
        existingMovie.setActors(movieDTO.getActors());
        existingMovie.setReleaseDate(movieDTO.getReleaseDate());
        existingMovie.setDuration(movieDTO.getDuration());
        existingMovie.setDescription(movieDTO.getDescription());
        existingMovie.setComing(movieDTO.isComing());
        if (movieDTO.getImgPath() != null) { // Nếu hình không thay đổi
            existingMovie.setImagePath(movieDTO.getImgPath());
        }
        existingMovie.setTrailerPath(movieDTO.getTrailerPath());
        existingMovie.setTagMovie(tagMovieRepository.getReferenceById(movieDTO.getTagId()));
        // Lấy model thể loại
        List<Genre> genres = new ArrayList<>();
        for (String genreName: movieDTO.getGenres()) {
            Genre genre = genreRepository.findByName(genreName);
            genres.add(genre);
        }
        existingMovie.setGenres(genres);
        Movie movie = movieRepository.save(existingMovie);
        return convertToMovieDTO(movie);
    }

    @Override
    public MovieDTO delete(Long id) {
        Movie existingMovie = movieRepository.getReferenceById(id);
        existingMovie.setDeleted(true);
        Movie movie = movieRepository.save(existingMovie);
        return convertToMovieDTO(movie);
    }

//    @Override
//    public MovieDTO forceDelete(Long id) {
//        Movie movie = movieRepository.getReferenceById(id);
//        MovieDTO movieDTO = convertToMovieDTO(movie);
//        movieRepository.delete(movie);
//        return movieDTO;
//    }

    @Override
    public List<MovieDTO> getAllByNameLikeAndComing(String name, boolean coming) {
        List<Movie> movieList = movieRepository.findAllByNameLikeAndComingOrderByIdDesc("%"+name+"%", coming);
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for(Movie movie : movieList ) {
            movieDTOList.add(convertToMovieDTO(movie));
        }
        return movieDTOList;
    }

    @Override
    public List<MovieDTO> getAllByGenreId(Long id) {
        List<Movie> movieList = movieRepository.findMoviesByGenreId(id);
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for(Movie movie : movieList) {
            movieDTOList.add(convertToMovieDTO(movie));
        }
        return movieDTOList;
    }

    @Override
    public List<MovieDTO> getTop6ByComingAndIdDesc() {
        List<Movie> movieList = movieRepository.findTop6ByComingAndDeletedOrderByIdDesc(true, false);
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for(Movie movie : movieList) {
            movieDTOList.add(convertToMovieDTO(movie));
        }
        return movieDTOList;
    }

    @Override
    public MovieDTO restore(Long id) {
        Movie existingMovie = movieRepository.getReferenceById(id);
        existingMovie.setDeleted(false);
        Movie movie = movieRepository.save(existingMovie);
        return convertToMovieDTO(movie);
    }
}
