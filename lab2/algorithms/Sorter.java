package Algoritmi.lab2.algorithms;

import Algoritmi.lab2.SortResult;

public interface Sorter<T extends Comparable<T>> {

    SortResult sort(T[] array);

    String getName();
}
