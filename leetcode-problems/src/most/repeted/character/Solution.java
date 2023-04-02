package most.repeted.character;

import java.util.HashMap;
import java.util.Map;

import static util.Assert.printAndAssert;

public class Solution {
    public static void main(String[] args) {
        final String s = "afdsdddddddewrewr2343fdsf32420000000000000034";
        printAndAssert(() -> findMostRepeatedCharacter(s), "d");
    }

    private static String findMostRepeatedCharacter(String s) {
        byte maxOccured = 0;
        Character maxOccuredLeter = null;
        final Map<Character, Byte> occurrence = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (Character.isDigit(c)) continue;
            Byte count = occurrence.getOrDefault(c, (byte) 0);
            occurrence.put(c, ++count);
            if (count > maxOccured) {
                maxOccuredLeter = c;
                maxOccured = count;
            }
        }
        return maxOccuredLeter != null ? maxOccuredLeter + ":" + maxOccured : "no result";
    }
}
