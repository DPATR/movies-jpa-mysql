package com.lmig.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmig.movies.model.Genre;
import com.lmig.movies.model.Star;

@Repository
public interface StarRepository extends JpaRepository<Star, Integer> {

    void deleteById(Integer Id);
    
    Star findByName(String name);
}
