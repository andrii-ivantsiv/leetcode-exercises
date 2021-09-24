package excel.sheet.column.number;

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
            title += (alphabet.indexOf(chars[i]) + 1) * Math.pow(alphabet.length(), p);
        }
        return title;
    }
}
