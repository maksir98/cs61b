import java.util.*;

public class DemoCollection {
    public static List<String> getWords(String inputFileName){
        List<String> words = new ArrayList<String>();
        In in = new In(inputFileName);
        while (!in.isEmpty()) {
            String nextWord = in.readString();
            words.add(nextWord);
        }
        return words;
    }

    public static int countUniqueWords(List<String> words) {
        Set<String> ss = new HashSet<>();
        for (String s : words){
            ss.add(s);
        }
        return ss.size();
    }

    public static Map<String, Integer> collectWordCount(List<String> words,List<String> targets){
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String t : targets){
            counts.put(t, 0);
        }

        for (String s: words) {
            if (counts.containsKey(s)){
                counts.put(s, counts.get(s) + 1);
            }
        }
        return counts;
    }

}
