import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    CharacterComparator offByN = new OffByN(5);

    @Test
    public void TestEqualChars1(){
        char input1 = 'a';
        char input2 = 'f';
        boolean result =  offByN.equalChars(input1, input2);
        assertTrue(result);
    }

    @Test
    public void TestEqualChars2(){
        char input1 = 'f';
        char input2 = 'a';
        boolean result =  offByN.equalChars(input1, input2);
        assertTrue(result);
    }

    @Test
    public void TestEqualChars3(){
        char input1 = 'f';
        char input2 = 'h';
        boolean result =  offByN.equalChars(input1, input2);
        assertFalse(result);
    }
}
