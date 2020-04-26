package Map61B;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class testArrayMap {

    @Test
    public void testKeys() {
        ArrayMap<String, String> input = new ArrayMap<String, String>();
        input.put("one", "car");
        input.put("two", "bus");
        input.put("three", "bike");
        List<String> output = input.keys();
        List<String> expected = new ArrayList<String>();
        expected.add("one");
        expected.add("two");
        expected.add("three");
        System.out.println(output);
    }

    @Test
    public void testGet() {
        ArrayMap<String, String> input = new ArrayMap<String, String>();
        input.put("one", "car");
        input.put("two", "bus");
        input.put("three", "bike");
        String output = input.get("home");
        System.out.print(output);
    }
}
