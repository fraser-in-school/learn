package search;

import lombok.Data;

import java.util.*;

public class Problem_1631 {
    @Data
    class Position {
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] status = new int[row][col];
        int[][] dir = {{1, -1, 0, 0}, {0, 0, 1, -1}};
        List<Position> reachable = new ArrayList<>();

        // 将起始节点放入到数组中
        status[0][0] = 1;
        reachable.add(new Position(0, 0));
        if (0 == row - 1 && 0 == col - 1) {
            return 0;
        }

        int effort = 0;
        // effort set
        SortedSet<Integer> effortSet = new TreeSet<>();
        // push all the drop distance
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (i < row -1) {
                    Integer drop = Math.abs(heights[i + 1][j] - heights[i][j]);
                    effortSet.add(drop);
                }
                if (j < col - 1) {
                    Integer drop = Math.abs(heights[i][j] - heights[i][j + 1]);
                    effortSet.add(drop);
                }
            }
        }
        Iterator<Integer> iterator = effortSet.iterator();
        while(iterator.hasNext()) {
            // get more node
            effort = iterator.next();
            for(int i = 0; i < reachable.size(); i ++) {
                Position now = reachable.get(i);
                for (int j = 0; j < 4; j++) {
                    int x = now.x + dir[0][j];
                    int y = now.y + dir[1][j];
                    // 如果超过边界, 查找下一个方向
                    if (x < 0 || y < 0 || x >= row || y >= col)
                        continue;
                    // 如果是已经到达的节点, 查找下一个方向
                    if (status[x][y] == 1)
                        continue;
                    // 如果小于目前的最大落差, 可以加入可达数组
                    if (Math.abs(heights[x][y] - heights[now.x][now.y]) <= effort) {
                        // reach the target
                        if (x == row - 1 && y == col - 1) {
                            return effort;
                        }
                        reachable.add(new Position(x, y));
                        status[x][y] = 1;
                    }

                }
            }
        }
        return effort;
    }

    class Solution {
        public int minimumEffortPath(int[][] heights) {
            int southBorder = heights.length;
            int eastBorder = heights[0].length;

            int[][] costs = new int[heights.length][heights[0].length];
            for(int i = heights.length - 1; i >= 0; i--) {
                for(int j = heights[i].length - 1; j >= 0; j--) {

                    int thisHeight = heights[i][j];

                    if(i + 1 < southBorder || j + 1 < eastBorder) {
                        int costSouth = Integer.MAX_VALUE;
                        int costEast = Integer.MAX_VALUE;

                        if(i + 1 < southBorder) {
                            // can go south
                            int southCost = costs[i + 1][j];
                            costSouth = Math.max(Math.abs(thisHeight - heights[i+1][j]), southCost);
                        }

                        if(j + 1 < eastBorder) {
                            // can go east
                            int eastCost = costs[i][j+1];
                            costEast = Math.max(Math.abs(thisHeight - heights[i][j+1]), eastCost);
                        }

                        int minCost = Math.min(costSouth, costEast);

                        pushUpdates(heights, costs, i, j, minCost);

                    }

                }
            }

            return costs[0][0];


        }

        public void pushUpdates(int[][] heights, int[][] costs, int i, int j, int updatedCost) {

            costs[i][j] = updatedCost;

            int thisHeight = heights[i][j];

            // push north
            if(i > 0) {
                int northCost = costs[i-1][j];
                int potentialCost = Math.max(Math.abs(thisHeight - heights[i-1][j]), updatedCost);
                if(potentialCost < northCost)
                    pushUpdates(heights, costs, i - 1, j, potentialCost);
            }

            // push south
            if(i + 1 < heights.length) {
                int southCost = costs[i+1][j];
                int potentialCost = Math.max(Math.abs(thisHeight - heights[i+1][j]), updatedCost);
                if(potentialCost < southCost)
                    pushUpdates(heights, costs, i + 1, j, potentialCost);
            }

            // push east
            if(j > 0) {
                int eastCost = costs[i][j-1];
                int potentialCost = Math.max(Math.abs(thisHeight - heights[i][j-1]), updatedCost);
                if(potentialCost < eastCost)
                    pushUpdates(heights, costs, i, j - 1, potentialCost);
            }

            // push west
            if(j + 1 < heights[i].length) {
                int westCost = costs[i][j+1];
                int potentialCost = Math.max(Math.abs(thisHeight - heights[i][j+1]), updatedCost);
                if(potentialCost < westCost)
                    pushUpdates(heights, costs, i, j + 1, potentialCost);
            }

        }
    }
}
