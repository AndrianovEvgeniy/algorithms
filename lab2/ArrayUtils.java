package Algoritmi.lab2;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {

    private static final Random RANDOM = new Random();

    public static Integer[] generateIntegerArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(1000);
        }
        return array;
    }

    public static Character[] generateCharacterArray(int size) {
        Character[] array = new Character[size];
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < size; i++) {
            array[i] = alphabet.charAt(RANDOM.nextInt(alphabet.length()));
        }
        return array;
    }

    public static <T> void printArray(T[] array) {
        if (array.length <= 30) {
            System.out.println(Arrays.toString(array));
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < 10; i++) {
                sb.append(array[i]).append(", ");
            }
            sb.append("... ").append(array.length - 20).append(" элементов ... ");
            for (int i = array.length - 10; i < array.length; i++) {
                sb.append(array[i]);
                if (i < array.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            System.out.println(sb);
        }
    }
}