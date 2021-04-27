package tree;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Problem_508 {
    static Integer ZERO = new Integer(0);

    Map<Integer, Integer> sumCountMap = new HashMap<>();

    int maxCount = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        sumCountMap.clear();
        if (root == null) {
            return new int[]{};
        }
        LinkedList<Integer> sumList = new LinkedList<>();
        getSum(root);
        List<Integer> ans = new ArrayList<>();
        int p = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = sumCountMap.entrySet().stream().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getValue().equals(maxCount)) {
                ans.add(entry.getKey());
            }
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }


    int getSum(TreeNode node) {
        if (node == null)
            return ZERO;
        int sum = node.val + getSum(node.left) + getSum(node.right);
        sumCountMap.merge(sum, 1, (oldValue, newValue) -> oldValue + newValue);
        maxCount = Math.max(maxCount, sumCountMap.get(sum));
        return sum;
    }
}
