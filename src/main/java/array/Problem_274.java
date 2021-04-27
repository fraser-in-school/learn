package array;

public class Problem_274 {
    public static void main(String[] args) {
        int[] list = {3, 0, 6, 1, 5};
        System.out.println(hIndex(list));
    }

    static int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        for (int c : citations) {
            if (c < n) {
                buckets[c] ++;
            } else {
                buckets[n] ++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i --) {
            count += buckets[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }
}
