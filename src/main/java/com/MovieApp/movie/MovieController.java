package com.MovieApp.movie;

import com.MovieApp.review.Review;
import com.MovieApp.review.ReviewService;
import com.MovieApp.user.User;
import com.MovieApp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final ReviewService reviewService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping({"/", "/movies"})
    public String showMoviesPage(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("module", "movies");

        return "movies";
    }

    @GetMapping(path = "movies/{movieId}")
    public String showMoviesItemPage(@PathVariable Long movieId,
                                     Model model) {
        Movie movie = movieService.getMovieById(movieId);
        model.addAttribute("movie", movie);
        model.addAttribute("isFav", false);

        List<Review> reviews = reviewService.getMovieAllReviews(movieId);
        model.addAttribute("reviews", reviews);

        model.addAttribute("newReview", new Review());

        return "movie";
    }

    @PostMapping(path = "movies/{movieId}")
    public String addNewReview(@PathVariable Long movieId,
                               @ModelAttribute("newReview") Review review,
                               Authentication authentication) {
        Movie movie = movieService.getMovieById(movieId);

        String username = authentication.getName();
        User user = userService.findUserByUsername(username);

        review.setMovie(movie);
        review.setDate(LocalDate.now());
        review.setUser(user);

        reviewService.addNewReview(review);

        return "redirect:/movies/" + movieId;
    }

    @GetMapping("add-movie")
    public String showAddMoviePage(Model model) {
        model.addAttribute("module", "add-movie");
        model.addAttribute("newMovie", new Movie());
        model.addAttribute("posterUrl");

        return "add-movie";
    }

    @PostMapping("/add-movie")
    public String addMovie(@ModelAttribute("newMovie") Movie movie,
                           @RequestParam("file") MultipartFile posterFile) {
        try {
            movieService.saveMovie(movie, posterFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/movies";
    }
}
