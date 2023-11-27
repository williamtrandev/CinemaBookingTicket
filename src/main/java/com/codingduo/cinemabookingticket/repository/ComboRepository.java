package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {
    List<Combo> findAllByDeleted(boolean deleted);
}
