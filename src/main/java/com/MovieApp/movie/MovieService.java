package com.MovieApp.movie;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    public final MovieRepository movieRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with such ID isn't exist"));
    }

    public void saveMovie(Movie movie, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadPath + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
        }

        movie.setPosterUrl(file.getOriginalFilename());

        movieRepository.save(movie);
    }
}
