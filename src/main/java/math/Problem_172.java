package math;

class Problem_172 {
    public int trailingZeroes(int n) {
        int count = 0;
        for (int i = 5; i <= n; i ++) {
            int j = i;
            while (j >= 5 && j % 5 ==0) {
                count ++;
                j /= 5;
            }
        }
        return count;

        /**
         *  return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
         */
    }


}