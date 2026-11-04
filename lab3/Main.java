package Algoritmi.lab3;

import Algoritmi.lab3.model.PathResult;
import Algoritmi.lab3.service.MatrixSolver;
import Algoritmi.lab3.util.MatrixVisualizer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MatrixSolver solver = new MatrixSolver();

        System.out.println("Лабораторная работа (Задача 17)");
        System.out.println("1 - Использовать тестовый пример 5x5");
        System.out.println("2 - Ввести размеры и матрицу вручную");
        System.out.print("Выбор: ");

        int choice = scanner.hasNextInt() ? scanner.nextInt() : 1;
        int n, m;
        int[][] a;

        if (choice == 2) {
            System.out.print("Введите n (число строк) и m (число столбцов): ");
            n = scanner.nextInt();
            m = scanner.nextInt();
            a = new int[n][m];
            System.out.println("Введите элементы матрицы (" + n + "x" + m + "):");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] = scanner.nextInt();
                }
            }
        } else {
            // Тестовая матрица 5x5
            n = 5;
            m = 5;
            a = new int[][]{
                    {3, 1, 4, 1, 5},
                    {9, 2, 6, 5, 3},
                    {5, 8, 9, 7, 9},
                    {3, 2, 3, 8, 4},
                    {6, 2, 6, 4, 3}
            };
        }

        System.out.println("\nИсходная матрица штрафов A[1.." + n + ", 1.." + m + "]:");
        MatrixVisualizer.printMatrix(a);

        // Пункт а)
        PathResult resA = solver.solveTaskA(a);
        MatrixVisualizer.printResult("РЕШЕНИЕ ПУНКТА (а): из любой клетки строки 1 в строку n", resA, a);

        // Пункт б)
        PathResult resB = solver.solveTaskB(a);
        MatrixVisualizer.printResult("РЕШЕНИЕ ПУНКТА (б): строго из клетки (1, 1) в клетку (" + n + ", " + m + ")", resB, a);
    }
}