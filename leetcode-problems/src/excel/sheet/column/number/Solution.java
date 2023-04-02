package excel.sheet.column.number;

import static util.Assert.printAndAssert;

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

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        printAndAssert(() -> titleToNumber("A"), 1);
        printAndAssert(() -> titleToNumber("AB"), 28);
        printAndAssert(() -> titleToNumber("BB"), 54);
        printAndAssert(() -> titleToNumber("ZY"), 701);
        printAndAssert(() -> titleToNumber("AKF"), 968);
        printAndAssert(() -> titleToNumber("AMJ"), 1024);
        printAndAssert(() -> titleToNumber("FXSHRXW"), 2147483647);
    }

    public static int titleToNumber(String columnTitle) {
        var titleNumber = 0;
        var titleChars = columnTitle.toCharArray();
        for (int i = 0, p = titleChars.length - 1; i < titleChars.length; i++, p--) {
            titleNumber += (alphabet.indexOf(titleChars[i]) + 1) * Math.pow(alphabet.length(), p);
        }
        return titleNumber;
    }
}
