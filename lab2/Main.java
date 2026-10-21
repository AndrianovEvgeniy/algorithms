package Algoritmi.lab2;

import Algoritmi.lab2.algorithms.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Лабораторная работа №2: Алгоритмы внутренней сортировки ===");

        System.out.print("Введите количество элементов массива (N): ");
        int size = scanner.nextInt();

        System.out.println("Выберите тип данных:");
        System.out.println("1 - Числовой (Integer)");
        System.out.println("2 - Символьный (Character)");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            Integer[] baseArray = ArrayUtils.generateIntegerArray(size);
            runTests(baseArray);
        } else if (choice == 2) {
            Character[] baseArray = ArrayUtils.generateCharacterArray(size);
            runTests(baseArray);
        } else {
            System.out.println("Неверный выбор типа данных.");
        }
    }

    private static <T extends Comparable<T>> void runTests(T[] baseArray) {
        System.out.println("\nИсходный массив:");
        ArrayUtils.printArray(baseArray);

        List<Sorter<T>> sorters = List.of(
                new InsertionSort<>(),
                new SelectionSort<>(),
                new ShakerSort<>(),
                new DualPivotQuickSort<>(),
                new HeapSort<>()
        );

        System.out.println("\n" + "=".repeat(85));
        System.out.printf("%-45s | %-16s | %-16s%n", "Алгоритм", "Сравнения", "Перестановки");
        System.out.println("=".repeat(85));

        T[] lastSortedArray = null;

        for (Sorter<T> sorter : sorters) {
            T[] copy = baseArray.clone();

            SortResult result = sorter.sort(copy);
            lastSortedArray = copy;

            System.out.printf("%-45s | %-16d | %-16d%n",
                    sorter.getName(),
                    result.comparisons(),
                    result.swaps());
        }

        System.out.println("=".repeat(85));

        System.out.println("\nОтсортированный массив (результат):");
        if (lastSortedArray != null) {
            ArrayUtils.printArray(lastSortedArray);
        }
    }
}