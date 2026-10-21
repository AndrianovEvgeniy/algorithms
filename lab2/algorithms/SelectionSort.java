package Algoritmi.lab2.algorithms;

import Algoritmi.lab2.SortResult;

public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public SortResult sort(T[] array) {
        long comparisons = 0;
        long swaps = 0;

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int minIndex = left;
            int maxIndex = right;

            comparisons++;
            if (array[minIndex].compareTo(array[maxIndex]) > 0) {
                swap(array, minIndex, maxIndex);
                swaps += 3;
            }

            // Идем шагом по 2 элемента (парное сравнение дает ровно 1.5 сравнения на элемент вместо 2)
            int i = left + 1;
            while (i < right - 1) {
                int smaller = i;
                int larger = i + 1;

                comparisons++;
                if (array[i].compareTo(array[i + 1]) > 0) {
                    smaller = i + 1;
                    larger = i;
                }

                comparisons++;
                if (array[smaller].compareTo(array[minIndex]) < 0) {
                    minIndex = smaller;
                    swaps++;
                }

                comparisons++;
                if (array[larger].compareTo(array[maxIndex]) > 0) {
                    maxIndex = larger;
                    swaps++;
                }

                i += 2;
            }

            // Если остался один непарный элемент
            if (i == right - 1) {
                comparisons++;
                if (array[i].compareTo(array[minIndex]) < 0) {
                    minIndex = i;
                    swaps++;
                } else {
                    comparisons++;
                    if (array[i].compareTo(array[maxIndex]) > 0) {
                        maxIndex = i;
                        swaps++;
                    }
                }
            }

            if (minIndex != left) {
                swap(array, left, minIndex);
                swaps += 3;
            }

            if (maxIndex == left) {
                maxIndex = minIndex;
            }

            if (maxIndex != right) {
                swap(array, right, maxIndex);
                swaps += 3;
            }

            left++;
            right--;
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