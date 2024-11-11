package tdbv.lab1;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class SetTest {
    Set testset;

    @Before
    public void setup() {
        testset = new Set();
    }


    @Test
    public void constructorTest(){
        assertTrue(testset instanceof Set);
    }

    @Test
    public void insertTest(){
        Set s = new Set();

        assertTrue(s.toArray().length == 0);
        
        s.insert(10);

        assertTrue(s.toArray().length == 1);
        assertTrue(s.toArray()[0] == 10);

        s.insert(5);

        assertTrue(s.toArray().length == 2);
        assertTrue(s.toArray()[0] == 5);
        assertTrue(s.toArray()[1] == 10);

        
        s.insert(10);
        assertTrue(s.toArray().length == 2);
        assertTrue(s.toArray()[0] == 5);
        assertTrue(s.toArray()[1] == 10);

    }

    
}
