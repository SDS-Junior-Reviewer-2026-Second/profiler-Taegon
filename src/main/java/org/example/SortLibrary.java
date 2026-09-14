package org.example;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class SortLibrary {
    private int swapCount;

    public final void sort(List<Integer> data) {
        swapCount = 0;
        doSort(data);
    }

    public final int getSwapCount() {
        return swapCount;
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    protected abstract void doSort(List<Integer> data);

    protected void swap(List<Integer> arr, int i, int j) {
        swapCount++;
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static Map<String, SortLibrary> all() {
        Map<String, SortLibrary> libraries = new LinkedHashMap<>();
        for (SortLibrary library : Arrays.asList(new BubbleSort(), new SelectionSort(), new HeapSort())) {
            libraries.put(library.getName(), library);
        }
        return libraries;
    }
}

class BubbleSort extends SortLibrary {
    @Override
    protected void doSort(List<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
}

class SelectionSort extends SortLibrary {
    @Override
    protected void doSort(List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i) > arr.get(j)) {
                    swap(arr, i, j);
                }
            }
        }
    }
}

class HeapSort extends SortLibrary {
    @Override
    protected void doSort(List<Integer> arr) {
        int n = arr.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private void heapify(List<Integer> arr, int size, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < size && arr.get(left) > arr.get(largest)) {
            largest = left;
        }
        if (right < size && arr.get(right) > arr.get(largest)) {
            largest = right;
        }
        if (largest != root) {
            swap(arr, root, largest);
            heapify(arr, size, largest);
        }
    }
}
