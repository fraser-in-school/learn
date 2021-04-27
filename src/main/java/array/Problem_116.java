package array;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_116 {
    public static void main(String[]  args) {
        String string = new String();
        Node node = new Node();
        node.setVal(null);
        System.out.println(node.getVal());
        // testForQueue();
    }

    static class Node {
        public Integer val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public Integer getVal(){
            return this.val;
        }
    };
    static void testForQueue() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(null);
        queue.offer(null);
        int index = 0;
        while( ! queue.isEmpty()) {
            System.out.println(index ++ );
            queue.poll();
        }
        Node node = new Node(1, null, null, null);
        node.next = null;
    }
    static class Solution {
        public static Node connect(Node root) {
            Queue<Node> queue = new LinkedList<Node>();
            Node current = root;
            queue.offer(current);
            while( ! queue.isEmpty()) {
                Node nextNode = queue.poll();
                if (current != null) {
                    if (current.left != null)
                        queue.offer(current.left);
                    if (current.right != null)
                        queue.offer(current.right);
                    current.next = nextNode;
                }
                current = nextNode;
            }
            // 将最右的节点的 next 设为空
            current = root;
            while(current != null) {
                current.next = null;
                current = current.right;
            }
            return root;
        }
    }
}
