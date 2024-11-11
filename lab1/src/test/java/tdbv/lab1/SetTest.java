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
    
}
