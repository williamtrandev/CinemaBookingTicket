package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
