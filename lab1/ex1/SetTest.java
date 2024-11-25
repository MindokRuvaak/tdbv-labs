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
        testSet.insert(10);

        assertTrue(testSet.member(10));
        assertFalse(testSet.member(5));
        assertFalse(testSet.member(15));
    }

    @Test
    public void intersectTestSC(){
        Set a = new Set();
        a.insert(10);
        a.insert(15);
        Set b = new Set();
        b.insert(5);
        b.insert(20);
        b.insert(10);

        Set res = a.intersect(b);
        assertTrue(res.member(10));
        assertTrue(res.toArray().length == 1);
        assertFalse(res.member(15));
        assertFalse(res.member(5));
        assertFalse(res.member(20));
    }

    @Test
    public void intersectTestBC(){
        Set a = new Set();
        a.insert(10);
        a.insert(15);

        Set b = new Set();
        b.insert(10);
        
        Set res = a.intersect(b);
        
        assertTrue(res.member(10));
        assertTrue(res.toArray().length == 1);
        assertFalse(res.member(15));
        assertFalse(res.member(5));

    }

    @Test
    public void distinctClosedTest(){
        Set s  = new Set();
        s.insert(0);
        s.insert(1);
        s.insert(2);
        assertFalse(s.distinctClosed( (a,b)-> a+b ));
        assertFalse(s.distinctClosed( (a,b)-> a-b ));
        
        assertTrue(testSet.distinctClosed( (a,b)-> a+b ));
        assertTrue(testSet.distinctClosed( (a,b)-> a-b ));
    }
    
}
