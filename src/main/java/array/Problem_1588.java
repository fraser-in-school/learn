package array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Problem_1588 {
    public static void main(String[] args){
        int[] testArr = {7, 6, 8, 6};
        System.out.println(sumOddLengthSubarrays(testArr));
    }

    public static int sumOddLengthSubarrays(int[] arr) {
        int length = arr.length;
        int sum = 0;
        int multipiler;
        int row_num = (length + 1) / 2;
        List<Integer> multipilerList = new ArrayList<Integer>();
        List<Integer> increaseNumberList = new ArrayList<Integer>();
        int index = 1;
        if (length % 2 == 1) {
            for(int item : arr) {
                // 从1开始，第奇数个数
                int no = index ++;
                if (no <= (length + 1) / 2) {
                    multipiler = row_num * no - no * no / 2;
                } else {
                    multipiler = multipilerList.get(length - no);
                }

                multipilerList.add(multipiler);
                sum += item * multipiler;
            }
        } else {
            int temp = length / 2;
            multipiler = temp;
            while(index < (length + 1)  / 2) {

                sum += (arr[index - 1] + arr[length - index]) * multipiler;
                multipiler += -- temp;
                index ++;
            }
        }

        // System.out.println(multipilerList);
        return sum;
    }
}
