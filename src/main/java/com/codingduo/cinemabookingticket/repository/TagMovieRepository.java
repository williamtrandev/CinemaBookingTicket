package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.TagMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMovieRepository extends JpaRepository<TagMovie, Long> {
}
