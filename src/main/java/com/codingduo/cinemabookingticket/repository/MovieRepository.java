package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByNameLike(String name);

    @Query("SELECT DISTINCT m FROM Movie m LEFT JOIN FETCH m.genres WHERE m.id = :id")
    Movie findMovieWithGenresBy(Long id);
}
