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
import com.lmig.movies.model.Genre;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lmig.movies.repository.GenreRepository;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GenreIntegrationTests {
    
    Integer saveId;
    Integer genre1Count;
    Integer genre3Count;
    Integer genre4Count;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Before
    public void testFindAll1() {
        System.out.println("in before");
        List<Genre> genre1 = genreRepository.findAll();
        //JUNIT assertion:
        genre1Count = genre1.size();
        //Hamcrest assertion:
        //assertThat(note1.size(), is(greaterThanOrEqualTo(0)));
        //assertThat(note1.size(), is(equalTo(2)));
        System.out.println("# of genres = " + genre1.size());
    }
    
    @Test
    public void testCreate() {
        System.out.println("in create");
        Genre genre2 = new Genre();
        genre2.setName("Junit Genre Integration Test");
        Genre savedGenre2 = genreRepository.save(genre2);
        System.out.println("savedGenre2 = " + savedGenre2.getId() + " " + savedGenre2.getName());
        saveId = savedGenre2.getId();
        List<Genre> genre3 = genreRepository.findAll();
        genre3Count = genre3.size();
        assertTrue(genre3Count == (genre1Count + 1));
        System.out.println("# of genres = " + genre3.size());
        System.out.println("in delete");
        genreRepository.deleteById(saveId);  
     }
    
     @After
     public void testFindAll2() {
        System.out.println("in after");
        List<Genre> genre4 = genreRepository.findAll();
        genre4Count = genre4.size();
        assertTrue(genre4Count == genre1Count);
        //Hamcrest assertion:
        //assertThat(wrecks.size(), is(greaterThanOrEqualTo(0)));
        //assertThat(wrecks2.size(), is(equalTo(2)));
        System.out.println("# of genres = " + genre4.size());
     }

}
