package happy.number;

import java.time.Duration;
import java.time.Instant;

/**
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * Example 1:
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * Example 2:
 * Input: n = 2
 * Output: false
 */
public class Solution {
    public static void main(String[] args) {
        //System.out.println(isHappy(19)); //true
        //System.out.println(isHappy(2));  //false
        //System.out.println(isHappy(7));  //true
        //System.out.println(isHappy(11)); //false

        var start = Instant.now();
        for (int i = 1; i < 1000; i++) {
            System.out.printf("Number %d is happy %s%n", i, isHappy(i));
        }
        var end = Instant.now();
        System.out.printf("Execution duration %d ms%n", Duration.between(start, end).toMillis());
    }

    public static boolean isHappy(int n) {
        var sumSquares = 0;
        var digitsCount = (int) (Math.log10(n) + 1);
        var digits = new int[digitsCount];
        for (int i = n; i > 0; i /= 10) {
            digits[--digitsCount] = i % 10;
            sumSquares += digits[digitsCount] * digits[digitsCount];
        }

        if (digits.length == 1) {
            return digits[0] == 1 || digits[0] == 7;
        } else {
            return sumSquares == 1 || isHappy(sumSquares);
        }
    }
}
