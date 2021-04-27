package math;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem_829 {
    public int consecutiveNumbersSum(int N) {
        int count = 1;
        for (int i = 2; i * (i + 1) <= 2 * N; i++) {
            if ((N - (i + 1) * i / 2) % i == 0)
                count ++;
        }
        return count;
    }

    public int calDistance(String str, char tag) {
        char[] codes = str.toCharArray();
        int maxStep = -1;
        int tagIndex = 0;
        while(tagIndex < codes.length) {
            if (codes[tagIndex] == tag)
                break;
            tagIndex ++;
        }
        for (int i = tagIndex; i < codes.length; i ++) {
                if (codes[i] == tag) {
                    int step = i - tagIndex - 1;
                    if (step > maxStep)
                        maxStep = step;
                    tagIndex = i;
                }
        }
        return maxStep;
    }

    static byte[][] map =
            {{11, 12, 15, 18, 20},
             {16, 20, 20, 20, 21},
             {15, 25, 29, 20, 23},
             {14, 20, 20, 20, 24},
             {13, 20, 25, 20, 29}};

    /**
     * 查找最优路径
     * @param map  地形数据
     * @param start   起始点
     * @param end    结束点
     * @param stepDrop   单步落差
     * @return
     */
    public static List<List<Point>> findPath(byte[][] map, Point start, Point end, byte stepDrop){
        ArrayList<ArrayList<Point>> queueList = new ArrayList<>();
        ArrayList<Point> queue = new ArrayList<>();

        // 标记数据
        byte[][] status = new byte[map.length][map[0].length];

        // 返回结果
        List<List<Point>> ans = new ArrayList<>();
        // 加入起始节点
        queue.add(start);
        status[start.x][start.y] = 1;
        queueList.add(queue);

        int[][] dir = {{1, -1, 0, 0}, {0, 0, 1, -1}};
        int index = 0;
        int pathLong = 1;
        int lastMaxIndex = 1;

        // 标记是否这一次搜索是否找到了新的节点
        // 如果新的节点找到了, 就可以重置 index
        boolean findNew = false;
        // 记录是否已经找到节点, 如果找到, 则 index 不重置
        boolean findTarget = false;
        while(index < queueList.size()) {
            ArrayList<Point> thisPath = queueList.get(index);

            // 当前队列小于路径长度, 说明是无效的路径, 跳过
            if (thisPath.size() < pathLong) {
                index ++;
                continue;
            }
            Point now = thisPath.get(thisPath.size() - 1);

            // 判断是不是终点
            if (now.x == end.x && now.y == end.y) {
                ans.add(thisPath);
                index ++;
                findTarget = true;
                continue;
            }

            // 找到这次可行的节点
            ArrayList<Point> cache = new ArrayList<>();
            for(int i = 0; i < 4; i ++) {
                int x = now.x + dir[0][i];
                int y = now.y + dir[1][i];
                if (x >= 0 && x < map.length
                        && y >= 0 && y < map[0].length && status[x][y] != 1
                        && Math.abs(map[now.x][now.y] - map[x][y]) <= stepDrop ) {
                    cache.add(new Point(x, y));
                }
            }
            // 剩下的加入新的队列
            for (int i = 1; i < cache.size(); i ++) {
                ArrayList<Point> newQueue = (ArrayList<Point>) thisPath.clone();
                newQueue.add(new Point(cache.get(i).x, cache.get(i).y));
                status[cache.get(i).x][cache.get(i).y] = 1;
                queueList.add(newQueue);
            }
            // 将第一个可行的节点加入当前的队列
            if ( ! cache.isEmpty()) {
                thisPath.add(new Point(cache.get(0).x, cache.get(0).y));
                status[cache.get(0).x][cache.get(0).y] = 1;
                findNew = true;
            }
            index ++;
            if (index >= lastMaxIndex && ! findTarget) {
                if (findNew == true) {
                    index = 0;
                    findNew = false;
                    lastMaxIndex = queueList.size();
                } else {
                    break;
                }

            }
        }

        return ans;
    }

    public static void dfs(byte[][] map, List<Point> path, Point end, byte stepDrop) {
        
    }
    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "(" + x +"," + y+")";
        }
    }
    public static void main(String[] args) {
        byte stepDrop=5;
        Point start=new Point(0,0);
        Point end=new Point(2,2);
        List<List<Point>> paths = findPath(map, start, end, stepDrop);
        for (List<Point> path : paths) {
            System.out.println(path);
        }
    }

}
