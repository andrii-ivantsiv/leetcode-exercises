package optimal.partition.string;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static util.Assert.printAndAssert;

/**
 * Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
 * <p>
 * Return the minimum number of substrings in such a partition.
 * <p>
 * Note that each character should belong to exactly one substring in a partition.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abacaba"
 * Output: 4
 * Explanation:
 * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
 * It can be shown that 4 is the minimum number of substrings needed.
 * Example 2:
 * <p>
 * Input: s = "ssssss"
 * Output: 6
 * Explanation:
 * The only valid partition is ("s","s","s","s","s","s").
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists of only English lowercase letters.
 */
public class Solution {
    public static void main(String[] args) {
        printAndAssert(() -> partitionString("hdklqkcssgxlvehva"), 4);
        printAndAssert(() -> partitionString("aaaaaaaa"), 8);
        printAndAssert(() -> partitionString("abacaba"), 4);
        printAndAssert(() -> partitionStringV1("hdklqkcssgxlvehva"), 4);
        printAndAssert(() -> partitionStringV1("aaaaaaaa"), 8);
        printAndAssert(() -> partitionStringV1("abacaba"), 4);
    }

    public static int partitionString(String s) {
        final ConcurrentHashMap<Character, Integer> letterScores = new ConcurrentHashMap<>();
        final AtomicInteger counter = new AtomicInteger(1);
        s.chars().mapToObj(c -> (char) c).forEach(c -> {
            int score = letterScores.compute(c, (k, v) -> v == null ? 1 : v + 1);
            if (score > 1) {
                counter.incrementAndGet();
                letterScores.clear();
                letterScores.put(c, 1);
            }
        });
        return counter.get();
    }

    public static int partitionStringV1(String s) {
        HashSet<Character> uniqueLetters = new HashSet<>();
        int counter = 1;
        for (char c : s.toCharArray()) {
            if (!uniqueLetters.add(c)) {
                ++counter;
                uniqueLetters.clear();
                uniqueLetters.add(c);
            }
        }
        return counter;
    }
}
