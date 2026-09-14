package org.example;

import java.util.List;

public class Profiler {
    private List<Integer> data;
    private SortLibrary library;

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public void setLib(SortLibrary library) {
        this.library = library;
    }

    public void runLib() {
        library.sort(data);
    }

    public void showResult() {
        System.out.println(getResultSummary());
    }

    public String getResultSummary() {
        return "Library : " + library.getName() + System.lineSeparator()
                + "Result  : " + data + System.lineSeparator()
                + "Swaps   : " + library.getSwapCount();
    }
}
