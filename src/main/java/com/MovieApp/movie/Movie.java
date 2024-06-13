package com.MovieApp.movie;

import com.MovieApp.review.Review;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private Integer year;
    private String genre;
    private String country;
    private String director;
    private String posterUrl;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    public Movie(
            String title,
            String description,
            Integer year,
            String genre,
            String country,
            String director,
            String posterUrl) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.genre = genre;
        this.country = country;
        this.director = director;
        this.posterUrl = posterUrl;
    }

}
