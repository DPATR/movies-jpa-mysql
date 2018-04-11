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
import com.lmig.movies.model.Director;
import com.lmig.movies.model.Movie;
import com.lmig.movies.repository.DirectorRepository;
import com.lmig.movies.repository.MovieRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    
    @Autowired
    DirectorRepository directorRepository;
    
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    @GetMapping("/directors")
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }
    
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }
    
    @GetMapping("/directors/{id}")
    public Director getDirectorById(@PathVariable(value = "id") Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director", "id", id));
    }
    
//    @GetMapping("movies/{title}")
//    public List<Movie> getMovieByTitle(@PathVariable(value = "title") String title){
//    public List<Movie> movie = movieRepository.findByTitle("Gladiator");
//        return movieRepository.findByTitle("Gladiator");
//    }
    
    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }
    
    @PostMapping("/directors")
    public Director createDirector(@Valid @RequestBody Director director) {
        return directorRepository.save(director);
    }
    
  //name,about
  //'John Smith','John'
    
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
    
    @PutMapping("/directors/{id}")
    public Director updateDirector(@PathVariable(value = "id") Integer id,
                                           @Valid @RequestBody Director directorDetails) {

        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director", "id", id));

        director.setName(directorDetails.getName());
        director.setAbout(directorDetails.getAbout());
        Director updatedDirector = directorRepository.save(director);
        return updatedDirector;
    }
    
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable(value = "id") Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movieRepository.delete(movie);
        //Sets 204 status
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/directors/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable(value = "id") Integer id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director", "id", id));

        directorRepository.delete(director);
        //Sets 204 status
        return ResponseEntity.ok().build();
    }
    
    
}
