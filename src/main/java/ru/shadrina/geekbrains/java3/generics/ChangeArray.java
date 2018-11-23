package ru.shadrina.geekbrains.java3.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeArray<T> {
    private T[] inputArray;


    public ChangeArray(T[] inputArray) {
        this.inputArray = inputArray;
    }

    public T[] changeArray(int i, int j) throws Exception {
        if ((i < 0 || i >= inputArray.length) || (j < 0 || j >= inputArray.length)) {
            throw new Exception("Index out of border");
        }

        if (i == j) {
            throw new Exception("Equal indexes");
        } else {
            final List<T> output = new ArrayList<>(Arrays.asList(inputArray));
            final T elementI = inputArray[i];
            final T elementJ = inputArray[j];
            output.set(j, elementI);
            output.set(i, elementJ);
            T[] outputArray = (T[]) output.toArray();
            return outputArray;
        }
    }
}
