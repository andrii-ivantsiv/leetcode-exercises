package count.total.number.colored.cells;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static util.Assert.printAndAssert;

/**
 * There exists an infinitely large two-dimensional grid of uncolored unit cells. You are given a positive integer n,
 * indicating that you must do the following routine for n minutes:
 * <p>
 * At the first minute, color any arbitrary unit cell blue.
 * Every minute thereafter, color blue every uncolored cell that touches a blue cell.
 * Below is a pictorial representation of the state of the grid after minutes 1, 2, and 3.
 * <p>
 * <p>
 * Return the number of colored cells at the end of n minutes.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: 1
 * Explanation: After 1 minute, there is only 1 blue cell, so we return 1.
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: 5
 * Explanation: After 2 minutes, there are 4 colored cells on the boundary and 1 in the center, so we return 5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 */
class Solution {

    public static void main(String[] args) {
        printAndAssert(() -> coloredCellsV2(1), 1L);
        printAndAssert(() -> coloredCellsV2(2), 5L);
        printAndAssert(() -> coloredCellsV2(3), 13L);
        printAndAssert(() -> coloredCellsV2(4), 25L);
        printAndAssert(() -> coloredCellsV2(5), 41L);
        printAndAssert(() -> coloredCellsV2(30), 1741L);
        printAndAssert(() -> coloredCellsV2(85), 14281L);
        printAndAssert(() -> coloredCellsV2(100000), 19999800001L);
    }

    public static long coloredCellsV2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 5;
        }
        if (n == 3) {
            return 13;
        }
        long cellCount = 13L;
        for (int i = 3; i < n; i++) {
            cellCount += 12L + (i - 3L) * 4L;
        }
        return cellCount;
    }

    // too slow with big numbers
    public static long coloredCellsV1(int n) {
        if (n == 1) {
            return 1;
        }
        final Set<Cell> cells = new HashSet<>();
        final Cell initialCell = new Cell(0, 0);
        cells.add(initialCell);
        for (int i = 1; i < n; i++) {
            Set<Cell> newNeighbors = new HashSet<>();
            for (Cell cell : cells) {
                newNeighbors.addAll(cell.neighbors());
            }
            cells.addAll(newNeighbors);
        }
        return cells.size();
    }

    static class Cell {
        private final int x;
        private final int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object object) {
            if (object instanceof Cell) {
                final Cell that = (Cell) object;
                return this.x == that.x && this.y == that.y;
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Set<Cell> neighbors() {
            final Set<Cell> cells = new HashSet<>();
            cells.add(new Cell(x, y + 1));
            cells.add(new Cell(x + 1, y));
            cells.add(new Cell(x, y - 1));
            cells.add(new Cell(x - 1, y));
            return cells;
        }
    }
}
