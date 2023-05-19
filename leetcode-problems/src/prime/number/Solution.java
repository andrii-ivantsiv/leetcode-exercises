package prime.number;

import java.util.ArrayList;
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
        printAndAssert(() -> findPrimes(2772), List.of(11, 7, 3, 3, 2, 2));
        printAndAssert(() -> findPrimes(8), List.of(2, 2, 2));
    }

    static List<Integer> findPrimes(int number) {
        int result = number;
        final List<Integer> resultPrimes = new ArrayList<>();
        final List<Integer> primes = primeNumbers(number);
        int primeIndex = primes.size() - 1;
        while (result > 1) {
            int remain = result % primes.get(primeIndex);
            if (remain == 0) {
                resultPrimes.add(primes.get(primeIndex));
                result = result / primes.get(primeIndex);
            } else {
                primeIndex--;
            }
        }
        return resultPrimes;
    }

    static List<Integer> primeNumbers(int number) {
        final List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= number / 2; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= i; j++) {
                if (i % j == 0 && i != j) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        return primes;
    }
}
