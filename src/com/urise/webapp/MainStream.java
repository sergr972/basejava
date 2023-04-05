package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 3, 2, 3};
        int[] array2 = {9, 8};

        System.out.println(minValue(array1));
        System.out.println(minValue(array2));
        System.out.println(oddOrEven(new ArrayList<>(Arrays.asList(1, 2, 3, 3, 2, 3))));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {

        return integers.stream().filter(n -> n % 2 != integers.stream()
                        .mapToInt(Integer::intValue)
                        .sum() % 2)
                .collect(Collectors.toList());
    }
}
