package factorial.trailing.zeroes;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * <p>
 * Example 2:
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * <p>
 * Example 3:
 * Input: n = 0
 * Output: 0
 */
public class Solution {

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

    public static void main(String[] args) {
        //TODO: very slow solution
        System.out.println(trailingZeroes(3));
        System.out.println(trailingZeroes(5));
        System.out.println(trailingZeroes(0));
        System.out.println(trailingZeroes(10000));

        /*var start = Instant.now();
        System.out.println(factorialV1(10000));
        var end = Instant.now();
        System.out.println("Factorial V1 = " + Duration.between(start, end).getNano());

        start = Instant.now();
        System.out.println(factorialV2(10000));
        end = Instant.now();
        System.out.println("Factorial V2 = " + Duration.between(start, end).getNano());

        start = Instant.now();
        System.out.println(factorialV3(Integer.MAX_VALUE));
        end = Instant.now();
        System.out.println("Factorial V3 = " + Duration.between(start, end).getNano());*/
    }

    public static int trailingZeroes(int n) {
        var start = Instant.now();
        if (n == 0) {
            return 0;
        }
        var f = factorialV3(n);
        var end = Instant.now();
        System.out.println("Factorial = " + Duration.between(start, end).getNano());
        start = Instant.now();
        var c = 0;
        char[] chars = f.toString().toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i] == '0') {
                c++;
            } else {
                break;
            }
        }
        end = Instant.now();
        System.out.println("Counting =  " + Duration.between(start, end).getNano());
        return c;
    }

    private static BigInteger factorialV1(int n) {
        var f = BigInteger.ONE;
        for (long i = 2L; i <= n; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }

    private static BigInteger factorialV2(int n) {
        var f = BigInteger.ZERO;
        if (n > 1000) {
            var m = (int) (n / 2);
            var future = CompletableFuture.supplyAsync(() -> multiply(1, m))
                    .thenCombine(CompletableFuture.supplyAsync(() -> multiply(m + 1, n)), BigInteger::multiply);
            try {
                f = future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            f = multiply(1, n);
        }
        return f;
    }

    private static BigInteger factorialV3(int n) {
        var f = BigInteger.ONE;
        if (n > 1000) {
            var p = (int) (n / AVAILABLE_PROCESSORS);
            var tasks = new ArrayList<Future<BigInteger>>();
            for (int i = 0, s = 0; i < AVAILABLE_PROCESSORS; i++, s += p) {
                var from = s + 1;
                var to = i == AVAILABLE_PROCESSORS - 1 ? n : s + p;
                tasks.add(EXECUTOR.submit(() -> multiply(from, to)));
            }
            for (var task : tasks) {
                try {
                    f = f.multiply(task.get());
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
            EXECUTOR.shutdownNow();
            return f;
        } else {
            return multiply(1, n);
        }
    }

    private static BigInteger multiply(final int from, final int to) {
        var result = BigInteger.valueOf(from);
        for (long i = from + 1; i <= to; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
