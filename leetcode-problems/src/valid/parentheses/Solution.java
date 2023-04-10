package valid.parentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    }

    public static boolean isValid(String s) {
        //TODO: fix algorithm
        if (s == null || s.isBlank() || !isEven(s.length())) {
            return false;
        }
        final Map<Character, List<Integer>> brackets = new HashMap<>();
        final char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (brackets.containsKey(chars[i])) {
                brackets.get(chars[i]).add(i);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                brackets.put(chars[i], indexes);
            }
        }
        if (sameSize(brackets.get('('), brackets.get(')'))) {
            brackets.remove('(');
            brackets.remove(')');
        }
        if (sameSize(brackets.get('{'), brackets.get('}'))) {
            brackets.remove('{');
            brackets.remove('}');
        }
        if (sameSize(brackets.get('['), brackets.get(']'))) {
            brackets.remove('[');
            brackets.remove(']');
        }
        return brackets.isEmpty();
    }

    private static boolean sameSize(List<Integer> listOne, List<Integer> listTwo) {
        return listOne != null && listTwo != null && listOne.size() == listTwo.size();
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
