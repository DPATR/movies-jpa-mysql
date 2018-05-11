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
import com.lmig.movies.model.Star;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lmig.movies.repository.StarRepository;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StarIntegrationTests {
    
    Integer saveId;
    Integer star1Count;
    Integer star3Count;
    Integer star4Count;
    
    @Autowired
    private StarRepository starRepository;
    
    @Before
    public void testFindAll1() {
        System.out.println("in before");
        List<Star> star1 = starRepository.findAll();
        //JUNIT assertion:
        star1Count = star1.size();
        //Hamcrest assertion:
        //assertThat(note1.size(), is(greaterThanOrEqualTo(0)));
        //assertThat(note1.size(), is(equalTo(2)));
        System.out.println("# of stars = " + star1.size());
    }
    

    @Test
    public void testCreate() {
        System.out.println("in create");
        Star star2 = new Star();
        star2.setName("Junit Star Integration Test");
        star2.setAbout("Junit Star");
        Star savedStar2 = starRepository.save(star2);
        System.out.println("savedStar2 = " + savedStar2.getId() + " " + savedStar2.getName() + " " + savedStar2.getAbout());
        saveId = savedStar2.getId();
        List<Star> star3 = starRepository.findAll();
        star3Count = star3.size();
        assertTrue(star3Count == (star1Count + 1));
        System.out.println("# of stars = " + star3.size());
        System.out.println("in delete");
        starRepository.deleteById(saveId);  
     }
    
     @After
     public void testFindAll2() {
        System.out.println("in after");
        List<Star> star4 = starRepository.findAll();
        star4Count = star4.size();
        assertTrue(star4Count == star1Count);
        //Hamcrest assertion:
        //assertThat(wrecks.size(), is(greaterThanOrEqualTo(0)));
        //assertThat(wrecks2.size(), is(equalTo(2)));
        System.out.println("# of stars = " + star4.size());
     }

}
