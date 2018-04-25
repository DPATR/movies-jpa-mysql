package com.lmig.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lmig.movies.model.Director;
import com.lmig.movies.model.Genre;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer>{
    
    void deleteById(Integer Id);
    
    Director findByName(String name);
    
}
