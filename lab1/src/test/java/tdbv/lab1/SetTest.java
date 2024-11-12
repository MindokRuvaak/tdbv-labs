package tdbv.lab1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class SetTest {
    Set testSet;

    @Before
    public void setup() {
        testSet = new Set();
        testSet.insert(10);
    }


    @Test
    public void constructorTest(){
        assertTrue(testSet instanceof Set);
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

    @Test
    public void memberTest(){
        assertTrue(testSet.member(10));
        assertFalse(testSet.member(5));
        assertFalse(testSet.member(15));
    }

    @Test
    public void intersectTest(){
        Set a = new Set();
        a.insert(10);
        a.insert(15);
        Set b = new Set();
        b.insert(5);
        b.insert(20);
        b.insert(10);

        a.intersect(b);
        assertTrue(a.member(10));
        
    }
    
}
