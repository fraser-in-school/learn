package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem_172Test {

    Problem_172 problem_172 = new Problem_172();
    @Test
    void trailingZeroes() {
        assertEquals(0, problem_172.trailingZeroes(4));
        assertEquals(1, problem_172.trailingZeroes(5));
        assertEquals(2, problem_172.trailingZeroes(10));
        assertEquals(3, problem_172.trailingZeroes(15));
        assertEquals(7, problem_172.trailingZeroes(30));
    }
}