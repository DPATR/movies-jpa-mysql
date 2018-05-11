package com.lmig.movies;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lmig.movies.model.Movie;
import com.lmig.movies.repository.MovieRepository;


@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieIntegrationTests {

        Integer saveId;
        Integer movie1Count;
        Integer movie3Count;
        Integer movie4Count;
        
        @Autowired
        private MovieRepository movieRepository;
        
        @Before
        public void testFindAll1() {
            System.out.println("in before");
            List<Movie> movie1 = movieRepository.findAll();
            //JUNIT assertion:
            movie1Count = movie1.size();
            //Hamcrest assertion:
            //assertThat(note1.size(), is(greaterThanOrEqualTo(0)));
            //assertThat(note1.size(), is(equalTo(2)));
            System.out.println("# of movies = " + movie1.size());
        }
        
        @Test
        public void testCreate() {
            System.out.println("in create");
            Movie movie2 = new Movie();
            movie2.setTitle("Junit Movie Integration Test");
            movie2.setYear(2018);
            movie2.setImage_url("test.jpg");
            movie2.setCertificate("PG-13");
            movie2.setRuntime(115);
            movie2.setImdb_rating((float) 8.5);
            movie2.setDescription("This is a dummy movie");
            movie2.setMetascore(68);
            movie2.setVotes(10000);
            movie2.setGross(20000000);
            Movie savedMovie2 = movieRepository.save(movie2);
            System.out.println("savedMovie2 = " + savedMovie2.getId() + " " + savedMovie2.getTitle() + " " + savedMovie2.getYear());
            saveId = savedMovie2.getId();
            List<Movie> movie3 = movieRepository.findAll();
            movie3Count = movie3.size();
            assertTrue(movie3Count == (movie1Count + 1));
            System.out.println("# of movies = " + movie3.size());
            System.out.println("in delete");
            movieRepository.deleteById(saveId);  
         }
        
         @After
         public void testFindAll2() {
            System.out.println("in after");
            List<Movie> movie4 = movieRepository.findAll();
            movie4Count = movie4.size();
            assertTrue(movie4Count == movie1Count);
            //Hamcrest assertion:
            //assertThat(wrecks.size(), is(greaterThanOrEqualTo(0)));
            //assertThat(wrecks2.size(), is(equalTo(2)));
            System.out.println("# of movies = " + movie4.size());
         }

}
