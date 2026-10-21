package Algoritmi.lab2.algorithms;

import Algoritmi.lab2.SortResult;

public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public SortResult sort(T[] array) {
        long comparisons = 0;
        long swaps = 0;

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                comparisons++;
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(array, i, minIndex);
                swaps++;
            }
        }

        return new SortResult(comparisons, swaps);
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public String getName() {
        return "Сортировка выбором (Selection Sort)";
    }
}