package test;

public class Niu {
    public int getMex(int[] A, int n) {
        int[]  status = new int[n + 1];
        for (int i = 0; i < A.length; i ++) {
            if (A[i] <= n && status[A[i]] == 0)
                status[A[i]] = 1;
        }
        for (int i = 1; i <= n; i ++) {
            if (status[i] == 0) {
                return i;
            }
        }
        return n + 1;
    }

    public int getMaxSegs(String S, String[] dics, int n) {
        char[] str = S.toCharArray();
        StringBuilder sb = new StringBuilder(S);
        int p = 0;
        int count  = 0;
        for (int i = 0; i < str.length; i ++) {
            String tmp = sb.substring(p, i + 1);
            for (int j = 0; j < dics.length; j ++) {
                if (tmp.contains(dics[j])) {
                    count ++;
                    p = i + 1;
                    break;
                }
            }
        }
        return count;
    }
}
