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
import com.lmig.movies.model.Genre;
import com.lmig.movies.model.Movie;
import com.lmig.movies.model.Star;
import com.lmig.movies.repository.DirectorRepository;
import com.lmig.movies.repository.GenreRepository;
import com.lmig.movies.repository.MovieRepository;
import com.lmig.movies.repository.StarRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    
    @Autowired
    DirectorRepository directorRepository;
    
    @Autowired
    StarRepository starRepository;
    
    @Autowired
    GenreRepository genreRepository;
    
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    @GetMapping("/directors")
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }
    
    @GetMapping("/stars")
    public List<Star> getAllStars() {
        return starRepository.findAll();
    }
    
    @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
    
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }
    
    @GetMapping("/movies/{id}/directors")
    public List<Director> getMovieDirectors(@PathVariable(value = "id") Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        return movie.getDirector(); 
    }
    
    @GetMapping("/movies-by-id")
    public List<Movie> getAllMoviesById() {
        return movieRepository.findAllByOrderByIdDesc();
    }
    
    @GetMapping("/movies-by-metascore")
    public List<Movie> getAllMoviesByMetascore() {
        return movieRepository.findAllByOrderByMetascoreDesc();
    }
    
    @GetMapping("/movies-by-certificate")
    public List<Movie> getAllMoviesByCertificate() {
        return movieRepository.findAllByOrderByCertificateAsc();
    }
    
    @GetMapping("/movie-by-one-certificate/{certificate}")
    public List<Movie> getMoviesByCertificate(@PathVariable(value = "certificate") String certificate) {
        return movieRepository.findByCertificate(certificate);
    }
    
    @GetMapping("/movie-by-title/{title}")
    public Movie getMovieByTitle(@PathVariable(value = "title") String title) {
        return movieRepository.findByTitle(title);
    }
    
    @GetMapping("/movies-by-title-containing/{title}")
    public List<Movie> getMovieByTitleContaining(@PathVariable(value = "title") String title) {
        return movieRepository.findByTitleContaining(title);
    }
    
    @GetMapping("/movies-by-one-year/{year}")
    public List<Movie> getMoviesByYear(@PathVariable(value = "year") Integer year) {
        return movieRepository.findByYear(year);
    }
    
    @GetMapping("/directors/{id}")
    public Director getDirectorById(@PathVariable(value = "id") Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director", "id", id));
    }
    
    @GetMapping("/directors/{id}/movies")
    public List<Movie> getDirectorMovies(@PathVariable(value = "id") Integer id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director", "id", id));
        return director.getMovies(); 
    }
    
    //Retrieve movies by Director name
    @GetMapping("/directors/movies/{name}")
    public List<Movie> getDirectorMoviesByName(@PathVariable(value = "name") String name) {
        Director director1 = directorRepository.findByName(name);
        Integer directorId = director1.getId();
        Director director2 = directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director", "id", directorId));
        return director2.getMovies(); 
    }
    
    @GetMapping("/stars/{id}")
    public Star getStarById(@PathVariable(value = "id") Integer id) {
        return starRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Star", "id", id));
    }
    
    @GetMapping("/stars/{id}/movies")
    public List<Movie> getStarMovies(@PathVariable(value = "id") Integer id) {
        Star star = starRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Star", "id", id));
        return star.getMovies(); 
    }
    
    //Retrieve movies by Star name
    @GetMapping("/stars/movies/{name}")
    public List<Movie> getStarMoviesByName(@PathVariable(value = "name") String name) {
        Star star1 = starRepository.findByName(name);
        Integer starId = star1.getId();
        Star star2 = starRepository.findById(starId)
                .orElseThrow(() -> new ResourceNotFoundException("Star", "id", starId));
        return star2.getMovies(); 
    }
    
    @GetMapping("/genres/{id}")
    public Genre getGenreById(@PathVariable(value = "id") Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));
    }
    
    @GetMapping("/genres/{id}/movies")
    public List<Movie> getGenreMovies(@PathVariable(value = "id") Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));
        return genre.getMovies(); 
    }
    
    //Retrieve movies by Genre name
    @GetMapping("/genres/movies/{name}")
    public List<Movie> getGenreMoviesByName(@PathVariable(value = "name") String name) {
        Genre genre1 = genreRepository.findByName(name);
        Integer genreId = genre1.getId();
        Genre genre2 = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", genreId));
        return genre2.getMovies(); 
    }
    
    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }
    
    @PostMapping("/directors")
    public Director createDirector(@Valid @RequestBody Director director) {
        return directorRepository.save(director);
    }
    
    @PostMapping("/stars")
    public Star createStar(@Valid @RequestBody Star star) {
        return starRepository.save(star);
    }
    
    @PostMapping("/genres")
    public Genre createGenre(@Valid @RequestBody Genre genre) {
        return genreRepository.save(genre);
    }
    
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
        movie.setDirector(movieDetails.getDirector());
        movie.setStar(movieDetails.getStar());
        movie.setGenre(movieDetails.getGenre());
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
    
    @PutMapping("/stars/{id}")
    public Star updateStar(@PathVariable(value = "id") Integer id,
                                           @Valid @RequestBody Star starDetails) {

        Star star = starRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Star", "id", id));

        star.setName(starDetails.getName());
        star.setAbout(starDetails.getAbout());
        Star updatedStar = starRepository.save(star);
        return updatedStar;
    }
    
    @PutMapping("/genres/{id}")
    public Genre updateGenre(@PathVariable(value = "id") Integer id,
                                           @Valid @RequestBody Genre genreDetails) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));

        genre.setName(genreDetails.getName());
        Genre updatedGenre = genreRepository.save(genre);
        return updatedGenre;
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
    
    @DeleteMapping("/stars/{id}")
    public ResponseEntity<?> deleteStar(@PathVariable(value = "id") Integer id) {
        Star star = starRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Star", "id", id));

        starRepository.delete(star);
        //Sets 204 status
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/genres/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable(value = "id") Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));

        genreRepository.delete(genre);
        //Sets 204 status
        return ResponseEntity.ok().build();
    }
    
}
