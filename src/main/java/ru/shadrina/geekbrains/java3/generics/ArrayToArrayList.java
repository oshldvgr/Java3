package ru.shadrina.geekbrains.java3.generics;

import java.util.ArrayList;

public class ArrayToArrayList<T> {

    public ArrayList<T> modify(T[] input) {
        final ArrayList<T> output = new ArrayList<>();
        for (T element : input) {
            output.add(element);
        }
        return output;
    }
}
