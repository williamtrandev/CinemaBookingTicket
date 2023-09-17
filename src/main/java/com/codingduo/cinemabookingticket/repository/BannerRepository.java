package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

}
