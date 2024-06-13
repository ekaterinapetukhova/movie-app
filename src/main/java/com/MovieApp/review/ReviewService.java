package com.MovieApp.review;

import com.MovieApp.movie.MovieRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public List<Review> getMovieAllReviews(Long id) {
        return reviewRepository.findReviewsByMovieId(id);
    }

    public void addNewReview(Review review) {
        reviewRepository.save(review);
    }

}
