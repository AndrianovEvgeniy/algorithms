package Algoritmi.lab2.algorithms;

import Algoritmi.lab2.SortResult;

public class DualPivotQuickSort<T extends Comparable<T>> implements Sorter<T> {

    private long comparisons;
    private long swaps;

    @Override
    public SortResult sort(T[] array) {
        comparisons = 0;
        swaps = 0;
        if (array != null && array.length > 1) {
            quickSort(array, 0, array.length - 1);
        }
        return new SortResult(comparisons, swaps);
    }

    private void quickSort(T[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        // Для 2 элементов — всего 1 сравнение
        if (high - low == 1) {
            comparisons++;
            if (array[low].compareTo(array[high]) > 0) {
                swap(array, low, high);
                swaps++;
            }
            return;
        }

        // Для 3 элементов — максимум 2-3 сравнения
        if (high - low == 2) {
            comparisons++;
            if (array[low].compareTo(array[low + 1]) > 0) {
                swap(array, low, low + 1);
                swaps++;
            }
            comparisons++;
            if (array[low + 1].compareTo(array[high]) > 0) {
                swap(array, low + 1, high);
                swaps++;
                comparisons++;
                if (array[low].compareTo(array[low + 1]) > 0) {
                    swap(array, low, low + 1);
                    swaps++;
                }
            }
            return;
        }

        // Сепараторы в некрайних позициях: low + 1 и high - 1
        swap(array, low, low + 1);
        swaps++;
        swap(array, high, high - 1);
        swaps++;

        comparisons++;
        if (array[low].compareTo(array[high]) > 0) {
            swap(array, low, high);
            swaps++;
        }

        T pivot1 = array[low];
        T pivot2 = array[high];

        int lt = low + 1;
        int gt = high - 1;
        int k = low + 1;

        while (k <= gt) {
            comparisons++;
            if (array[k].compareTo(pivot1) < 0) {
                swap(array, k, lt);
                swaps++;
                lt++;
                k++;
            } else {
                comparisons++;
                if (array[k].compareTo(pivot2) > 0) {
                    swap(array, k, gt);
                    swaps++;
                    gt--;
                } else {
                    k++;
                }
            }
        }

        lt--;
        gt++;
        swap(array, low, lt);
        swaps++;
        swap(array, high, gt);
        swaps++;

        quickSort(array, low, lt - 1);
        quickSort(array, lt + 1, gt - 1);
        quickSort(array, gt + 1, high);
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public String getName() {
        return "Быстрая сортировка (Dual-Pivot QuickSort)";
    }
}