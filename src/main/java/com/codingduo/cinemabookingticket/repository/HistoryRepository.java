package com.codingduo.cinemabookingticket.repository;


import com.codingduo.cinemabookingticket.model.History;
import com.codingduo.cinemabookingticket.model.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByCustomer(UserSystem customer);

    List<History> findHistoriesByDateBookingBetween(Date startOfWeek, Date endOfWeek);
}
