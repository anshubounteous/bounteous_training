package com.example.service_provider_management.repository;

import com.example.service_provider_management.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTargetUserId(Long targetUserId);
}
