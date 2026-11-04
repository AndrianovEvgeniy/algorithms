package Algoritmi.lab3.util;

import Algoritmi.lab3.model.PathResult;
import Algoritmi.lab3.model.Point;

import java.util.List;

public class MatrixVisualizer {

    public static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%4d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void printPathVisualization(int[][] matrix, List<Point> path) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] onPath = new boolean[n][m];

        for (Point p : path) {
            onPath[p.getRow() - 1][p.getCol() - 1] = true;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (onPath[i][j]) {
                    System.out.printf("[%2d] ", matrix[i][j]);
                } else {
                    System.out.printf(" %2d  ", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void printResult(String taskTitle, PathResult result, int[][] matrix) {
        System.out.println("\n--------------------------------------------------");
        System.out.println(taskTitle);
        System.out.println("--------------------------------------------------");

        if (!result.isReachable()) {
            System.out.println("Результат: Путь не существует (клетка недостижима).");
            return;
        }

        System.out.println("Минимальный штраф: " + result.getMinPenalty());
        System.out.print("Маршрут: ");
        List<Point> path = result.getPath();
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + (i + 1 < path.size() ? " -> " : "\n"));
        }

        System.out.println("\nВизуализация пути в матрице (клетки в скобках):");
        printPathVisualization(matrix, path);
    }
}