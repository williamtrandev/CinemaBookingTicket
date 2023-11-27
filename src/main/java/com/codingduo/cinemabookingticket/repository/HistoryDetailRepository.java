package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.HistoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryDetailRepository extends JpaRepository<HistoryDetail, Long> {
    List<HistoryDetail> findAllByShowTimeId(Long showtime_id);
}
