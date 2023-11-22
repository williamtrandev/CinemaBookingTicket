package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Customer;
import com.codingduo.cinemabookingticket.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByCustomer(Customer customer);
}
