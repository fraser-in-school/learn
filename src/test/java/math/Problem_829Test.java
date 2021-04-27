package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem_829Test {

    Problem_829 problem_829 = new Problem_829();
    @Test
    void consecutiveNumbersSum() {
        assertEquals(2, problem_829.consecutiveNumbersSum(5));
        assertEquals(3, problem_829.consecutiveNumbersSum(9));
        assertEquals(4, problem_829.consecutiveNumbersSum(15));
    }

    @Test
    void calDistance() {
        assertEquals(4, problem_829.calDistance("aaabccccbbbb", 'b'));
        assertEquals(0, problem_829.calDistance("aaabbbccc", 'b'));
        assertEquals(1, problem_829.calDistance("aaabcbdd", 'b'));
        assertEquals(-1, problem_829.calDistance("aaacccc", 'b'));
    }

}