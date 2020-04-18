import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars1 (){
        char input1 = 'a';
        char input2 = 'b';
        boolean result =  offByOne.equalChars(input1, input2);
        assertTrue(result);
    }

    @Test
    public void testEqualChars2 (){
        char input1 = 'r';
        char input2 = 'q';
        boolean result =  offByOne.equalChars(input1, input2);
        assertTrue(result);
    }

    @Test
    public void testEqualChars3 (){
        char input1 = 'a';
        char input2 = 'e';
        boolean result =  offByOne.equalChars(input1, input2);
        assertFalse(result);
    }

    @Test
    public void testEqualChars4 (){
        char input1 = 'a';
        char input2 = 'z';
        boolean result =  offByOne.equalChars(input1, input2);
        assertFalse(result);
    }
}