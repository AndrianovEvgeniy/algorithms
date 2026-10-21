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

        // построение кучи
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(array, n, i);
        }

        // извлечение максимума
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            swaps++;
            siftDown(array, i, 0);
        }

        return new SortResult(comparisons, swaps);
    }

    /**
     * Просеивание по методу Флойда (bottom-up):
     * 1) спуск до листа по большему ребёнку (1 сравнение на уровень),
     * 2) подъём вверх до места, где элемент должен стоять,
     * 3) сдвиг элементов пути вверх.
     */
    private void siftDown(T[] array, int size, int root) {
        // 1. Спуск до листа
        int j = root;
        while (true) {
            int left = 2 * j + 1;
            if (left >= size) {
                break;
            }
            int maxChild = left;
            int right = left + 1;
            if (right < size) {
                comparisons++;
                if (array[right].compareTo(array[left]) > 0) {
                    maxChild = right;
                }
            }
            j = maxChild;
        }

        // 2. Подъём: ищем первый узел на пути, который >= array[root]
        T x = array[root];
        while (j > root) {
            comparisons++;
            if (x.compareTo(array[j]) <= 0) {
                break;
            }
            j = (j - 1) / 2;
        }

        // 3. Сдвигаем путь от root до j вверх и ставим x на место j
        int d = depth(j) - depth(root);
        int k = root;
        for (int s = d - 1; s >= 0; s--) {
            int next = ((j + 1) >> s) - 1; // предок j на s уровней выше
            array[k] = array[next];
            k = next;
            swaps++;
        }
        array[k] = x;
    }

    private int depth(int index) {
        return 31 - Integer.numberOfLeadingZeros(index + 1);
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