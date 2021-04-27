package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem_240Test {
     Problem_240 problem_240 = new Problem_240();
     int[][] testMatrix1 = new int[][] {
             {1, 4, 7, 11, 15},
             {2, 5, 8, 12, 19},
             {3, 6, 9, 16, 22},
             {10, 13, 14, 17, 24},
             {18, 21, 23, 26, 30}
     };

     int[][] testMatrix2 = new int[][] {

     };
    @Test
    void searchMatrix() {
        assertEquals(true, problem_240.searchMatrix2(testMatrix1, 5));
    }
}