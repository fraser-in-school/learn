package tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Problem_508Test {
    Problem_508 problem508 = new Problem_508();
    @Test
    void findFrequentTreeSum() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
        assertArrayEquals(Arrays.stream(new int[]{2, -3, 4}).sorted().toArray(), Arrays.stream(problem508.findFrequentTreeSum(root)).sorted().toArray());

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(-5);
        assertArrayEquals(Arrays.stream(new int[]{2}).sorted().toArray(), Arrays.stream(problem508.findFrequentTreeSum(root2)).sorted().toArray());

        TreeNode root3 = null;
        assertArrayEquals(Arrays.stream(new int[]{}).sorted().toArray(), Arrays.stream(problem508.findFrequentTreeSum(root3)).sorted().toArray());

        TreeNode root4 = new TreeNode(3);
        root4.left = new TreeNode(1);
        root4.right = new TreeNode(5);

        root4.left.left = new TreeNode(0);
        root4.left.right = new TreeNode(3);
        root4.left.right.right = new TreeNode(3);

        root4.right.left = new TreeNode(4);
        root4.right.right = new TreeNode(6);

        assertArrayEquals(Arrays.stream(new int[]{6}).sorted().toArray(), Arrays.stream(problem508.findFrequentTreeSum(root4)).sorted().toArray());
    }
}