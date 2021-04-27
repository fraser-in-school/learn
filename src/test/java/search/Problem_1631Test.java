package search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem_1631Test {

    Problem_1631 problem_1631 = new Problem_1631();
    @Test
    void minimumEffortPath() {
        assertEquals(2, problem_1631.minimumEffortPath(new int[][]{
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        }));

        assertEquals(1, problem_1631.minimumEffortPath(new int[][]{
                {1, 2, 3},
                {3, 8, 4},
                {5, 3, 5}
        }));

        assertEquals(0, problem_1631.minimumEffortPath(new int[][]{
                {1, 2, 1, 1, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 2, 1}
        }));

        assertEquals(0, problem_1631.minimumEffortPath(new int[][]{
                {3}
        }));

        assertEquals(9, problem_1631.minimumEffortPath(new int[][]{
                {1, 10, 6, 7, 9, 10, 4, 9}
        }));
    }
}