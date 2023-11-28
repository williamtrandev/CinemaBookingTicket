package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.ComboHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboHistoryRepository extends JpaRepository<ComboHistory, Long> {
}
