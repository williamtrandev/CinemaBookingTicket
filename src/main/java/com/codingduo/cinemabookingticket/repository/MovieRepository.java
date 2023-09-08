package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
