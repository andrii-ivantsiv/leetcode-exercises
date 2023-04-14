package validate.stack.sequences;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

import static util.Assert.printAndAssert;

/**
 * Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4),
 * pop() -> 4,
 * push(5),
 * pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * Example 2:
 * <p>
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * All the elements of pushed are unique.
 * popped.length == pushed.length
 * popped is a permutation of pushed.
 */
public class Solution {
    public static void main(String[] args) {
        printAndAssert(() -> validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}), true);
        printAndAssert(() -> validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}), false);
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (popped.length != pushed.length) {
            return false;
        }
        final Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0, j = 0; i < pushed.length || j < popped.length; ) {
            if (Objects.equals(stack.peekLast(), popped[j])) {
                stack.pollLast();
                j++;
            } else if (!Objects.equals(stack.peekLast(), popped[j]) && i < pushed.length) {
                stack.addLast(pushed[i]);
                i++;
            } else {
                break;
            }
        }
        return stack.isEmpty();
    }
}
