package com.lmig.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lmig.movies.model.Genre;
import com.lmig.movies.model.Movie;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    void deleteById(Integer Id);
    
    Genre findByName(String name);
}
