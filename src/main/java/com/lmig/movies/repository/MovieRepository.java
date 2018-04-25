package com.lmig.movies.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lmig.movies.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    void deleteById(Integer Id);
    
    List<Movie> findAllByOrderByIdDesc();
  
    List<Movie> findAllByOrderByMetascoreDesc();
    
    List<Movie> findAllByOrderByCertificateAsc();
    
    List<Movie> findByCertificate(String certficate);
    
    List<Movie> findByYear(Integer year);
      
    Movie findByTitle(String title);
    
    List<Movie> findByTitleContaining(String title);
}
