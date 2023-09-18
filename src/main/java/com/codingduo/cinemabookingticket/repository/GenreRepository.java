package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("SELECT DISTINCT g FROM Genre g " +
            "INNER JOIN g.movies m " +
            "WHERE m.coming = false")
    List<Genre> findGenreNamesForShowingMovies();
    Genre findByName(String name);
}
