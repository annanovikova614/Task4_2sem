package com.company;

public class MyInsertionSort {
    public static <T extends Comparable<T>> T[] sort(T[] sortList, boolean[] allowList) {
        int start = 0;
        while (allowList[start]) {
            start++;
        }
        for (int i = start + 1; i < sortList.length; i++) {
            if (allowList[i]) continue;
            for (int j = i - 1; j >= start; j--) {
                if (allowList[j]) continue;
                boolean more = sortList[j].compareTo(sortList[i]) < 0;
                if (j == start || more) {
                    T temp = sortList[i];
                    for (int k = i - 1; k >= j; k--) {
                        if (allowList[k]) continue;
                        int toSwap = k + 1;
                        while (allowList[toSwap]) {
                            toSwap++;
                        }
                        sortList[toSwap] = sortList[k];
                    }
                    int toSwap = j + 1;
                    if (j == start && !more) toSwap = start;
                    while (allowList[toSwap]) {
                        toSwap++;
                    }
                    sortList[toSwap] = temp;
                    break;
                }
            }
        }
        return sortList;
    }
}