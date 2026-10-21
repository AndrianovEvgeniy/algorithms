package Algoritmi.lab2.algorithms;

import Algoritmi.lab2.SortResult;

public class ShakerSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public SortResult sort(T[] array) {
        long comparisons = 0;
        long swaps = 0;

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int lastSwap = left;

            // Проход слева направо
            for (int i = left; i < right; i++) {
                comparisons++;
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    swaps++;
                    lastSwap = i; // Запоминаем позицию последнего обмена
                }
            }
            right = lastSwap; // Сразу сдвигаем правую границу до последнего обмена

            if (left >= right) {
                break;
            }

            lastSwap = right;

            // Проход справа налево
            for (int i = right; i > left; i--) {
                comparisons++;
                if (array[i - 1].compareTo(array[i]) > 0) {
                    swap(array, i - 1, i);
                    swaps++;
                    lastSwap = i; // Запоминаем позицию последнего обмена
                }
            }
            left = lastSwap; // Сразу сдвигаем левую границу
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
        return "Шейкерная сортировка (Shaker Sort)";
    }
}