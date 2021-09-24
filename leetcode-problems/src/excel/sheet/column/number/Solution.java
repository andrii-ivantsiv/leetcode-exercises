package excel.sheet.column.number;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.
 * <p>
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * Example 1:
 * Input: columnTitle = "A"
 * Output: 1
 * <p>
 * Example 2:
 * Input: columnTitle = "AB"
 * Output: 28
 * <p>
 * Example 3:
 * Input: columnTitle = "ZY"
 * Output: 701
 * <p>
 * Example 4:
 * Input: columnTitle = "FXSHRXW"
 * Output: 2147483647
 */
public class Solution {

    private static final Map<Character, Integer> alphabet = new HashMap<>();

    static {
        alphabet.put('A', 1);
        alphabet.put('B', 2);
        alphabet.put('C', 3);
        alphabet.put('D', 4);
        alphabet.put('E', 5);
        alphabet.put('F', 6);
        alphabet.put('G', 7);
        alphabet.put('H', 8);
        alphabet.put('I', 9);
        alphabet.put('J', 10);
        alphabet.put('K', 11);
        alphabet.put('L', 12);
        alphabet.put('M', 13);
        alphabet.put('N', 14);
        alphabet.put('O', 15);
        alphabet.put('P', 16);
        alphabet.put('Q', 17);
        alphabet.put('R', 18);
        alphabet.put('S', 19);
        alphabet.put('T', 20);
        alphabet.put('U', 21);
        alphabet.put('V', 22);
        alphabet.put('W', 23);
        alphabet.put('X', 24);
        alphabet.put('Y', 25);
        alphabet.put('Z', 26);
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("A")); //1
        System.out.println(titleToNumber("AB")); //28
        System.out.println(titleToNumber("BB")); //54
        System.out.println(titleToNumber("ZY")); //701
        System.out.println(titleToNumber("AKF")); //968
        System.out.println(titleToNumber("AMJ")); //1024
        System.out.println(titleToNumber("FXSHRXW")); //2147483647
    }

    public static int titleToNumber(String columnTitle) {
        var title = 0;
        var chars = columnTitle.toCharArray();
        for (int i = 0, p = chars.length - 1; i < chars.length; i++, p--) {
            title += alphabet.get(chars[i]) * Math.pow(26, p);
        }
        return title;
    }
}
