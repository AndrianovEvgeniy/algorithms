package Algoritmi.lab3.service;

import Algoritmi.lab3.model.PathResult;
import Algoritmi.lab3.model.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MatrixSolver {

    private static final long INF = Long.MAX_VALUE / 2;

    // Пункт а): из любой клетки строки 1 в строку n
    public PathResult solveTaskA(int[][] a) {
        int n = a.length;
        int m = a[0].length;

        long[][] dp = new long[n][m];
        int[][] prevCol = new int[n][m];

        for (int j = 0; j < m; j++) {
            dp[0][j] = a[0][j];
            prevCol[0][j] = -1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long minVal = Long.MAX_VALUE;
                int bestK = -1;

                for (int dj = -1; dj <= 1; dj++) {
                    int k = j + dj;
                    if (k >= 0 && k < m) {
                        if (dp[i - 1][k] < minVal) {
                            minVal = dp[i - 1][k];
                            bestK = k;
                        }
                    }
                }

                dp[i][j] = a[i][j] + minVal;
                prevCol[i][j] = bestK;
            }
        }

        long minPenalty = Long.MAX_VALUE;
        int bestEndCol = -1;
        for (int j = 0; j < m; j++) {
            if (dp[n - 1][j] < minPenalty) {
                minPenalty = dp[n - 1][j];
                bestEndCol = j;
            }
        }

        List<Point> path = reconstructPath(prevCol, bestEndCol, n);
        return new PathResult(minPenalty, path, true);
    }

    // Пункт б): строго из клетки (1, 1) в клетку (n, m)
    public PathResult solveTaskB(int[][] a) {
        int n = a.length;
        int m = a[0].length;

        long[][] dp = new long[n][m];
        int[][] prevCol = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
            Arrays.fill(prevCol[i], -1);
        }

        dp[0][0] = a[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long minVal = INF;
                int bestK = -1;

                for (int dj = -1; dj <= 1; dj++) {
                    int k = j + dj;
                    if (k >= 0 && k < m && dp[i - 1][k] != INF) {
                        if (dp[i - 1][k] < minVal) {
                            minVal = dp[i - 1][k];
                            bestK = k;
                        }
                    }
                }

                if (minVal != INF) {
                    dp[i][j] = a[i][j] + minVal;
                    prevCol[i][j] = bestK;
                }
            }
        }

        long finalCost = dp[n - 1][m - 1];
        if (finalCost >= INF) {
            return PathResult.unreachable();
        }

        List<Point> path = reconstructPath(prevCol, m - 1, n);
        return new PathResult(finalCost, path, true);
    }

    private List<Point> reconstructPath(int[][] prevCol, int endCol, int n) {
        List<Point> path = new ArrayList<>();
        int currCol = endCol;
        for (int i = n - 1; i >= 0; i--) {
            path.add(new Point(i + 1, currCol + 1));
            currCol = prevCol[i][currCol];
        }
        Collections.reverse(path);
        return path;
    }
}