/*
 * Copyright (C) 2018, Liberty Mutual Group
 *
 * Created on Apr 10, 2018
 */

package com.lmig.movies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.lmig.movies.exception.ResourceNotFoundException;
import com.lmig.movies.model.Movie;
import com.lmig.movies.repository.MovieRepository;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }
    
    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }
    
  //title,year,image_url,certificate,runtime,imdb_rating,description,metascore,votes,gross
    //'Tomb Raider',2018,'https://ia.media-imdb.com/images/M/MV5BOTY4NDcyZGQtYmVlNy00ODgwLTljYTMtYzQ2OTE3NDhjODMwXkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_UX67_CR0,0,67,98_AL_.jpg','PG-13',118,6.8,'Lara Croft, the fiercely independent daughter of a missing adventurer, must push herself beyond her limits when she finds herself on the island where her father disappeared.',48,42587,52150000
    
    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable(value = "id") Integer id,
                                           @Valid @RequestBody Movie movieDetails) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movie.setTitle(movieDetails.getTitle());
        movie.setYear(movieDetails.getYear());
        movie.setImage_url(movieDetails.getImage_url());
        movie.setCertificate(movieDetails.getCertificate());
        movie.setRuntime(movieDetails.getRuntime());
        movie.setImdb_rating(movieDetails.getImdb_rating());
        movie.setDescription(movieDetails.getDescription());
        movie.setMetascore(movieDetails.getMetascore());
        movie.setVotes(movieDetails.getVotes());
        movie.setGross(movieDetails.getGross());
        Movie updatedMovie = movieRepository.save(movie);
        return updatedMovie;
    }
    
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable(value = "id") Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movieRepository.delete(movie);
        //Sets 204 status
        return ResponseEntity.ok().build();
    }
    
    
}
