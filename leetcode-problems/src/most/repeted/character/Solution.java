package most.repeted.character;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static util.Assert.printAndAssert;

public class Solution {
    public static void main(String[] args) {
        final String s = "afdsdddddddewrewr2343fdsf32420000000000000034";
        printAndAssert(() -> findMostRepeatedCharacter(s), "d:9");
        printAndAssert(() -> findMostRepeatedCharacterWithStreams(s), "d:9");
    }

    private static String findMostRepeatedCharacter(String s) {
        byte maxOccured = 0;
        Character maxOccuredLeter = null;
        final Map<Character, Byte> occurrence = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (!Character.isAlphabetic(c)) continue;
            Byte count = occurrence.getOrDefault(c, (byte) 0);
            occurrence.put(c, ++count);
            if (count > maxOccured) {
                maxOccuredLeter = c;
                maxOccured = count;
            }
        }
        return maxOccuredLeter != null ? maxOccuredLeter + ":" + maxOccured : "no result";
    }

    private static String findMostRepeatedCharacterWithStreams(String s) {
        final Map<Integer, Integer> occurrence = new ConcurrentHashMap<>();
        final AtomicInteger maxOccurredCount = new AtomicInteger();
        final AtomicReference<Character> maxOccurredLetter = new AtomicReference<>();
        s.chars().filter(Character::isAlphabetic).forEach(letter -> {
            int count = occurrence.compute(letter, (k, v) -> v == null ? 1 : ++v);
            if (count > maxOccurredCount.get()) {
                maxOccurredCount.set(count);
                maxOccurredLetter.set((char) letter);
            }
        });
        return maxOccurredLetter.get() != null ? maxOccurredLetter + ":" + maxOccurredCount : "no result";
    }
}
