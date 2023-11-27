package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {
    UserSystem findByEmail(String email);

    boolean existsByEmail(String email);
}
