package prime.number;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static util.Assert.printAndAssert;

/**
 * Write a program that calculates and prints a prime factorisation for the given number.
 * <p>
 * For example:
 * Input: 8, Output: 2, 2, 2
 * Input: 2772, Output: 2, 2, 3, 3, 7, 11
 */
public class Solution {

    public static void main(String... str) {
        printAndAssert(() -> findPrimes(2772), List.of(2, 2, 3, 3, 7, 11));
        printAndAssert(() -> findPrimes(8), List.of(2, 2, 2));
    }

    public static List<Integer> findPrimes(int number) {
        int result = number;
        final List<Integer> resultPrimes = new ArrayList<>();
        final Deque<Integer> primes = primeNumbers(number);
        while (result > 1 && primes.peekLast() != null) {
            if (result % primes.peekLast() == 0) {
                resultPrimes.add(primes.peekLast());
                result = result / primes.peekLast();
            } else {
                primes.pollLast();
            }
        }
        return resultPrimes;
    }

    static Deque<Integer> primeNumbers(int number) {
        final Deque<Integer> primes = new ArrayDeque<>();
        for (int i = 2; i <= number / 2; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= i; j++) {
                if (i % j == 0 && i != j) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.addFirst(i);
            }
        }
        return primes;
    }
}
