package simplify.path;

import java.util.Stack;

import static util.Assert.printAndAssert;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 * <p>
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 * <p>
 * The canonical path should have the following format:
 * <p>
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 * <p>
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 * <p>
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public class Solution {
    public static void main(String[] args) {
        printAndAssert(() -> simplifyPath("/a/./b/../../c/"), "/c");
        printAndAssert(() -> simplifyPath("/a//b////c/d//././/.."), "/a/b/c");
    }

    public static String simplifyPath(String path) {
        final Stack<String> folders = new Stack<>();
        for (String s : path.split("/")) {
            if ("..".equals(s)) {
                if (!folders.isEmpty()) {
                    folders.pop();
                }
            } else if (!s.isEmpty() && !s.isBlank() && !".".equals(s)) {
                folders.add(s);
            }
        }
        final StringBuilder canonicalPath = new StringBuilder();
        for (String s : folders) {
            canonicalPath.append("/").append(s);
        }
        return canonicalPath.length() == 0 ? "/" : canonicalPath.toString();
    }
}
