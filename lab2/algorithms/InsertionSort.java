package Algoritmi.lab2.algorithms;

import Algoritmi.lab2.SortResult;

public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public SortResult sort(T[] array) {
        long comparisons = 0;
        long swaps = 0;

        for (int i = 1; i < array.length; i++) {
            int j = i;

            while (j > 0) {
                comparisons++;
                if (array[j - 1].compareTo(array[j]) > 0) {
                    swap(array, j - 1, j);
                    swaps++;
                    j--;
                } else {
                    break;
                }
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
        return "Сортировка включением (Insertion Sort)";
    }
}