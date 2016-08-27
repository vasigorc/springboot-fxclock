/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.vasigorc.clock;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author vgorcinschi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
    
    @Autowired
    private ClockPane clockPane;
    
    public ApplicationTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void contextLoads(){}
    
    @Test
    public void clockNotNull(){
        assertNotNull(clockPane);
    }
    
    @Test
    public void getHeightOfClockTest(){
        Assert.assertTrue(clockPane.getH()==250);
    }
}
