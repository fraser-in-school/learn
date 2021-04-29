package array;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem_819 {
    static String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.replaceAll("[^a-zA-Z ,]", "").toLowerCase().split("[ , ]");
        // System.out.println(words);
        HashSet<String> strSet = new HashSet<String>(Arrays.asList(banned));
        return Arrays.stream(words)
                .filter(word -> ! word.equals("") && ! strSet.contains(word))
                .collect(Collectors.toMap(word -> word, num -> 1, (oldValue, newValue) -> oldValue + newValue))
                .entrySet().stream().max((left, right) -> left.getValue() - right.getValue()).get().getKey();
    }
    public static void main(String[] args) {
        System.out.println(mostCommonWord("a, a, a, a, b,b,b,c, c", new String[]{"a"}));
    }
}
