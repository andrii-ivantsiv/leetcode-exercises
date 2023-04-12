package removing.stars.from.string;

import static util.Assert.printAndAssert;

/**
 * You are given a string s, which contains stars *.
 * <p>
 * In one operation, you can:
 * <p>
 * Choose a star in s.
 * Remove the closest non-star character to its left, as well as remove the star itself.
 * Return the string after all stars have been removed.
 * <p>
 * Note:
 * <p>
 * The input will be generated such that the operation is always possible.
 * It can be shown that the resulting string will always be unique.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leet**cod*e"
 * Output: "lecoe"
 * Explanation: Performing the removals from left to right:
 * - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
 * - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
 * - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
 * There are no more stars, so we return "lecoe".
 * Example 2:
 * <p>
 * Input: s = "erase*****"
 * Output: ""
 * Explanation: The entire string is removed, so we return an empty string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters and stars *.
 * The operation above can be performed on s.
 */
public class Solution {
    public static void main(String[] args) {
        printAndAssert(() -> removeStars("leet**cod*e"), "lecoe");
        printAndAssert(() -> removeStars("erase*****"), "");
        printAndAssert(() -> removeStars("abb*cdfg*****x*"), "a");
    }

    public static String removeStars(String s) {
        final StringBuilder chars = new StringBuilder();
        int lastChar;
        for (char c : s.toCharArray()) {
            if (c != '*') {
                chars.append(c);
            } else if ((lastChar = chars.length() - 1) >= 0) {
                chars.deleteCharAt(lastChar);
            }
        }
        return chars.toString();
    }
}
