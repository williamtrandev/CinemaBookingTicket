package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByNameLikeAndComingOrderByIdDesc(String name, boolean coming);
    List<Movie> findAllByDeletedOrderByIdDesc(boolean deleted);

    @Query("SELECT DISTINCT m FROM Movie m LEFT JOIN FETCH m.genres WHERE m.id = :id")
    Movie findMovieWithGenresBy(Long id);

    @Query("SELECT DISTINCT m FROM Movie m " +
            "INNER JOIN m.genres g " +
            "WHERE g.id = :genreId AND m.deleted = false " +
            "ORDER BY m.id DESC")
    List<Movie> findMoviesByGenreId(Long genreId);

    List<Movie> findTop6ByComingAndDeletedOrderByIdDesc(boolean coming, boolean deleted);

}
