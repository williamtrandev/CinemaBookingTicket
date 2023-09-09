package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.TagMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagMovieRepository extends JpaRepository<TagMovie, Long> {
}
