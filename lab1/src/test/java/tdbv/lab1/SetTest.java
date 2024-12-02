package tdbv.lab1;

import static org.junit.Assert.assertArrayEquals;
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
    public void intersectTest2(){
        Set a  = new Set();
        a.insert(0);
        a.insert(1);

        Set b = new Set();
        b.insert(0);

        Set res1 = a.intersect(b);
        Set res2 = b.intersect(a);
        
        assertArrayEquals(res1.toArray(), new int[]{0});
        assertArrayEquals(res1.toArray(), res2.toArray());
    }
    
    @Test
    public void intersectTest3(){
        Set a  = new Set();
        a.insert(1);
        a.insert(2);
        a.insert(3);

        Set b = new Set();
        b.insert(6);
        b.insert(7);
        b.insert(8);


        Set c = new Set();

        Set res1 = a.intersect(b);
        Set res2 = b.intersect(a);
        Set res3 = a.intersect(c);
        Set res4 = c.intersect(a);

        assertArrayEquals(new int[]{}, res1.toArray());
        assertArrayEquals(res1.toArray(), res2.toArray());
        assertArrayEquals(res2.toArray(), res3.toArray());
        assertArrayEquals(res4.toArray(), res1.toArray());
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

    @Test
    public void distinctClosedTest2(){
        Set s1 = new Set();
        s1.insert(-1);
        s1.insert(-2);

        assertFalse(s1.distinctClosed((a,b)->a+b));
        assertFalse(s1.distinctClosed((a,b)->a-b));


        Set s2 = new Set();
        s2.insert(0);
        s2.insert(1);

        assertTrue(s2.distinctClosed((a,b)->a+b));
        assertFalse(s2.distinctClosed((a,b)->a-b));
    }
}
