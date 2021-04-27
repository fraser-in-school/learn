package dynamicPrograming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem_1423Test {

    Problem_1423 problem1423 = new Problem_1423();
    @Test
    void maxScore() {
        assertEquals(12, problem1423.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        assertEquals(4, problem1423.maxScore(new int[]{2, 2, 2}, 2));
        assertEquals(55, problem1423.maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
        assertEquals(1, problem1423.maxScore(new int[]{1, 1000, 1}, 1));
        assertEquals(202, problem1423.maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
    }
}