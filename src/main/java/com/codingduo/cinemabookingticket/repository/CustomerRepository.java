package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    boolean existsByEmail(String email);
}
