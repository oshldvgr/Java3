package ru.shadrina.geekbrains.java3.generics.fruits;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> boxUnit = new ArrayList<>();

    List<T> getBoxUnit() {
        return boxUnit;
    }

    void addFruit(T fruit) {
        boxUnit.add(fruit);
    }

    float getWeight() {
        int fruitsNumber = boxUnit.size();
        if (fruitsNumber == 0) {
            return 0.0f;
        } else {
            final Class<? extends Fruit> fruitClass = boxUnit.get(0).getClass();
            return fruitClass.getSimpleName().equals("Orange") ? (1.5f * fruitsNumber) : (1.0f * fruitsNumber);
        }
    }

    boolean compare(Box box) {
        return this.getWeight() == box.getWeight();
    }

    void putToBox(Box<T> box) {
        box.getBoxUnit().addAll(boxUnit);
        boxUnit.clear();
    }
}
