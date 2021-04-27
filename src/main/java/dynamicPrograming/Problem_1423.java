package dynamicPrograming;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Problem_1423 {
//    public int maxScore(int[] cardPoints, int k) {
//        AtomicInteger accumulator = new AtomicInteger();
//        int[] leftSum = Arrays.asList(cardPoints).stream().map(item -> {
//            accumulator.addAndGet(item);
//            return accumulator;
//        })
//    }

    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        // 在末尾和开头的地方各加上一个 0, s所以长度 + 2
        int[] leftSum = new int[len + 2];
        int[] rightSum = new int[len + 2];
        int tempL = 0;
        int tempR = 0;
        for (int i = 0; i < k; i ++) {
            tempL += cardPoints[i];
            leftSum[i + 1] = tempL; // 第一个地方放 0

            tempR += cardPoints[len - i - 1];
            rightSum[len - i] = tempR; // 最后一个地方放 0 len - i = rightSum.length - 2 - i = rightSum.length - i - 1 -1
        }
        int ans = 0;
        for (int i = 0; i <= k; i ++) {
            int sum = leftSum[i] + rightSum[len + 1 - k + i]; // 左边的第一个数(0) 应该与右边的倒数第 k + 1 个数相加
            if (sum > ans)
                ans = sum;
        }
        return  ans;
    }
}
