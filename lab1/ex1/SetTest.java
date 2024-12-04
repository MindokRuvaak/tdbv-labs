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

        a.intersect(b);
        assertTrue(a.member(10));
        assertTrue(a.toArray().length == 1);
        assertFalse(a.member(15));
        assertFalse(a.member(5));
        assertFalse(a.member(20));
    }

    @Test
    public void intersectTest2(){
        Set a  = new Set();
        a.insert(0);
        a.insert(1);

        Set b = new Set();
        b.insert(0);

        a.intersect(b);
        
        assertArrayEquals(a.toArray(), new int[]{0});
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

        a.intersect(b);
        b.intersect(c);

        assertArrayEquals(new int[]{}, a.toArray());
        assertArrayEquals(a.toArray(), b.toArray());
        assertArrayEquals(b.toArray(), c.toArray());
    }

    @Test
    public void intersectTestBC(){
        Set a = new Set();
        a.insert(10);
        a.insert(15);

        Set b = new Set();
        b.insert(10);
        
        a.intersect(b);
        
        assertTrue(a.member(10));
        assertTrue(a.toArray().length == 1);
        assertFalse(a.member(15));
        assertFalse(a.member(5));

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
