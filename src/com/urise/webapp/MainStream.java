package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class MainStream {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 3, 2, 3};
        int[] array2 = {9, 8};
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 2, 3));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 3, 2, 3, 4));

        System.out.println(minValue(array1));
        System.out.println(minValue(array2));
        System.out.println(oddOrEven(list1));
        System.out.println(oddOrEven(list2));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
//        return integers.stream().filter(n -> n % 2 != integers.stream()
//                .mapToInt(Integer::intValue).sum() % 2)
//                .collect(Collectors.toList());

        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(partitioningBy(x -> x % 2 == 0, toList()));
        return map.get(map.get(false).size() % 2 != 0);

    }
}
