package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_986 {
    public static void main(String[]  args) {
        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        Solution.intervalIntersection(A, B);
    }
    static class Solution {
        public static int[][] intervalIntersection(int[][] A, int[][] B) {
            List<Object> ans = new ArrayList<Object>();
            int p = 0;
            int q = 0;
            int index = 0;
            while(p < A.length && q < B.length) {
                // 初始化
                int a = A[p][0];
                int b = A[p][1];
                int c = B[q][0];
                int d = B[q][1];
                // 第一种情形 a, b < c, d
                if( b < c) {
                    p ++;
                    //2. a, b = c, d
                } else if (b == c) {
                    int[] ansItem = new int[2];
                    ansItem[0] = c;
                    ansItem[1] = b;
                    ans.add(ansItem);

                    p ++;

                } else if (b > c && c >= a) {
                    //3. a, c, b, d
                    if (b < d) {
                        int[] ansItem = new int[2];
                        ansItem[0] = c;
                        ansItem[1] = b;
                        ans.add(ansItem);

                        p ++;
                        //4. a, c, d, b
                    } else if ( b >= d ) {
                        int[] ansItem = new int[2];
                        ansItem[0] = c;
                        ansItem[1] = d;
                        ans.add(ansItem);

                        q ++;
                    }

                } else if (b > c && c < a) {
                    //5. c, d, a, b
                    if (d < a) {
                        q ++;
                        //6. c, a, d, b
                    } else if (d >= a && d < b) {
                        int[] ansItem = new int[2];
                        ansItem[0] = a;
                        ansItem[1] = d;
                        ans.add(ansItem);

                        q ++;
                        // 7. c, a, b, d
                    } else if (d >= a && d>= b) {
                        int[] ansItem = new int[2];
                        ansItem[0] = a;
                        ansItem[1] = b;
                        ans.add(ansItem);

                        p ++;
                    }
                }
            }
//            System.out.println(ans.toArray());

            int length = ans.size();
            int[][] ansList = new int[length][2];
            for(int i = 0; i < ans.size(); i ++) {
                int[] itemList = (int[])ans.get(i);
                ansList[i] = itemList;
            }
            return ansList;
        }
    }
}
