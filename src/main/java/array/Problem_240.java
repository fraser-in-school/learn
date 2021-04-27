package array;

public class Problem_240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        return binarySearch(matrix, 0, row - 1, 0, col - 1, target);
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int x = 0;
        int y = col -1;
        while (x < row && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                y--;
            } else
                x++;
        }
        return false;
    }

    boolean binarySearch(int[][] matrix, int rowLeft, int rowRight, int colLeft, int colRight, int target) {
        if (rowLeft > rowRight || colLeft > colRight) return false;

        int rowMid = (rowRight - rowLeft) / 2 + rowLeft;
        int colMid = (colRight - colLeft) / 2 + colLeft;

        if (matrix[rowMid][colMid] == target) {
            return true;
        } else if (matrix[rowMid][colMid] > target) {
            return binarySearch(matrix, rowLeft, rowRight, colLeft, colMid - 1, target) | binarySearch(matrix, rowLeft, rowMid - 1, colMid, colRight, target);
        } else  {
            return binarySearch(matrix, rowLeft, rowRight, colMid + 1, colRight, target) | binarySearch(matrix, rowMid + 1, rowRight, colLeft, colMid, target);
        }
    }
}
