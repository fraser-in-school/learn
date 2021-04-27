package tree;

public class Problem_129 {
    public int sumNumbers(TreeNode root) {
        return getVal(root, 0);
    }

    int getVal(TreeNode node, int fatherVal) {
        if (node.left == null && node.right ==null) {
            return fatherVal * 10 + node.val;
        }
        if (node == null) return 0;
//        int amount = 0;
//        if (node.left != null)
//            amount += getVal(node.left, fatherVal * 10 + node.val);
//        if (node.right != null) {
//            amount += getVal(node.right, fatherVal * 10 + node.val);
//        }
        // 使用尾递归来减少堆栈使用
        return getVal(node.left, fatherVal * 10 + node.val) + getVal(node.right, fatherVal * 10 + node.val);
        // return amount;
    }
}
