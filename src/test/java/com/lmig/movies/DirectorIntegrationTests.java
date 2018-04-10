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
import com.lmig.movies.model.Director;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lmig.movies.repository.DirectorRepository;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DirectorIntegrationTests {
    
    Integer saveId;
    
    @Autowired
    private DirectorRepository directorRepository;
    
    @Before
    public void testFindAll1() {
        System.out.println("in before");
        List<Director> director1 = directorRepository.findAll();
        //JUNIT assertion:
        assertTrue(director1.size() == 5);
        //Hamcrest assertion:
        //assertThat(note1.size(), is(greaterThanOrEqualTo(0)));
        //assertThat(note1.size(), is(equalTo(2)));
        System.out.println("# of directors = " + director1.size());
    }
    

    @Test
    public void testCreate() {
        System.out.println("in create");
        Director director2 = new Director();
        director2.setName("Junit Director Integration Test");
        director2.setAbout("Junit Director");
        Director savedDirector2 = directorRepository.save(director2);
        System.out.println("savedDirector2 = " + savedDirector2.getId() + " " + savedDirector2.getName() + " " + savedDirector2.getAbout());
        saveId = savedDirector2.getId();
        List<Director> director3 = directorRepository.findAll();
        assertTrue(director3.size() == 6);
        System.out.println("# of directors = " + director3.size());
        System.out.println("in delete");
        directorRepository.deleteById(saveId);  
     }
    
     @After
     public void testFindAll2() {
        System.out.println("in after");
        List<Director> director4 = directorRepository.findAll();
        assertTrue(director4.size() == 5);
        //Hamcrest assertion:
        //assertThat(wrecks.size(), is(greaterThanOrEqualTo(0)));
        //assertThat(wrecks2.size(), is(equalTo(2)));
        System.out.println("# of directors = " + director4.size());
     }

}
