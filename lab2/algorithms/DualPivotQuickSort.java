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

        if (high - low < 3) {
            manualSortSmall(array, low, high);
            return;
        }

        int pivotIndex1 = low + 1;
        int pivotIndex2 = high - 1;

        swap(array, low, pivotIndex1);
        swaps++;
        swap(array, high, pivotIndex2);
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
            }
            else {
                comparisons++;
                if (array[k].compareTo(pivot2) > 0) {
                    while (k < gt) {
                        comparisons++;
                        if (array[gt].compareTo(pivot2) > 0) {
                            gt--;
                        } else {
                            break;
                        }
                    }
                    swap(array, k, gt);
                    swaps++;
                    gt--;

                    comparisons++;
                    if (array[k].compareTo(pivot1) < 0) {
                        swap(array, k, lt);
                        swaps++;
                        lt++;
                    }
                    k++;
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

        quickSort(array, low, lt - 1);       // Левый подмассив (< P1)
        quickSort(array, lt + 1, gt - 1);   // Центральный подмассив (P1 <= x <= P2)
        quickSort(array, gt + 1, high);     // Правый подмассив (> P2)
    }

    private void manualSortSmall(T[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int j = i;
            while (j > low) {
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