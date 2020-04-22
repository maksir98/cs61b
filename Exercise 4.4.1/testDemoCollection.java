import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class testDemoCollection {
    @Test
    public void testGetWords() {
        String input = "test.txt";
        List<String> output = new ArrayList<String>();
        DemoCollection.getWords(input);
    }
}
