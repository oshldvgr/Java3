package ru.shadrina.geekbrains.java3.generics;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        String[] testArray = {"Moscow", "Madrid", "London", "Paris", "Rome", "Amsterdam", "Prague"};
        ChangeArray<String> changing = new ChangeArray<>(testArray);
        try {
            System.out.println(Arrays.toString(changing.changeArray(2, 5)));
        } catch (Exception e) {
            e.getMessage();
        }

        System.out.println(testArray);
        ArrayToArrayList<String> a = new ArrayToArrayList<>();
        System.out.println(a.modify(testArray));


    }
}
