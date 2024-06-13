package com.MovieApp.review;

import com.MovieApp.movie.Movie;
import com.MovieApp.user.User;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    @Column(columnDefinition = "text")
    private String content;
    private Integer rating;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review(
            String title,
            String content,
            Integer rating,
            LocalDate date,
            Movie movie,
            User user
    ) {
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.date = date;
        this.movie = movie;
        this.user = user;
    }
}
