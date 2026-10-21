package Algoritmi.lab2.algorithms;

import Algoritmi.lab2.SortResult;

public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public SortResult sort(T[] array) {
        long comparisons = 0;
        long swaps = 0;

        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (array[j].compareTo(key) > 0) {
                    array[j + 1] = array[j]; // Сдвиг элемента вправо
                    swaps++;
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }

        return new SortResult(comparisons, swaps);
    }

    @Override
    public String getName() {
        return "Сортировка включением (Insertion Sort)";
    }
}