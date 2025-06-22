package com.example.service_provider_management.review;

import com.example.service_provider_management.model.Review;
import com.example.service_provider_management.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * Save a new review submitted by a customer or provider.
     */
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Get all reviews written about a specific user.
     */
    public List<Review> getReviewsForUser(Long targetUserId) {
        return reviewRepository.findByTargetUserId(targetUserId);
    }
}
