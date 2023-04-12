package valid.parentheses;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static util.Assert.printAndAssert;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(]"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */
public class Solution {

    public static void main(String[] args) {
        printAndAssert(() -> isValid("()"), true);
        printAndAssert(() -> isValid("([)]"), false);
        printAndAssert(() -> isValid("([}}])"), false);
        printAndAssert(() -> isValid("))"), false);
    }

    public static boolean isValid(String s) {
        if (s == null || s.isBlank() || s.length() % 2 != 0) {
            return false;
        }
        final Map<Character, Character> openClosedBrackets = Map.of(
                '(', ')',
                '{', '}',
                '[', ']'
        );
        final Deque<Character> openBrackets = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (openClosedBrackets.containsKey(c)) {
                openBrackets.addLast(c);
            } else if (openBrackets.isEmpty() || openClosedBrackets.get(openBrackets.pollLast()) != c) {
                return false;
            }
        }
        return openBrackets.isEmpty();
    }
}
