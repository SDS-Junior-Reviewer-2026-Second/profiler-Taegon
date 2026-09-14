package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort {
    private int changeCnt = 0;

    // selection sort 오름차순 예시코드
    public void selectionSort(ArrayList<Integer> arr) {
        changeCnt = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i) > arr.get(j)) {
                    changeCnt++;
                    int temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }
        }
    }

    public int getChangeCnt() {
        return changeCnt;
    }

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(5,2,1,3,1,2,6,9));
        sort.selectionSort(arr);
        System.out.println(arr);
    }
}