package Algoritmi.lab2.algorithms;

import Algoritmi.lab2.SortResult;

public class HeapSort<T extends Comparable<T>> implements Sorter<T> {

    private long comparisons;
    private long swaps;

    @Override
    public SortResult sort(T[] array) {
        comparisons = 0;
        swaps = 0;
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            swaps++;

            siftDown(array, i, 0);
        }

        return new SortResult(comparisons, swaps);
    }

    private void siftDown(T[] array, int size, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < size) {
            comparisons++;
            if (array[left].compareTo(array[largest]) > 0) {
                largest = left;
            }
        }

        if (right < size) {
            comparisons++;
            if (array[right].compareTo(array[largest]) > 0) {
                largest = right;
            }
        }

        if (largest != root) {
            swap(array, root, largest);
            swaps++;
            siftDown(array, size, largest);
        }
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public String getName() {
        return "Пирамидальная сортировка (Heap Sort)";
    }
}