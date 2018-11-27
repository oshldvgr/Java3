package ru.shadrina.geekbrains.java3.generics.fruits;

public class TestBox {
    public static void main(String[] args) {
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();

        for (int i = 0; i < 3; i++) {
            orangeBox1.addFruit(new Orange());
        }

        for (int i = 0; i < 3; i++) {
            orangeBox2.addFruit(new Orange());
        }

        for (int i = 0; i < 6; i++) {
            appleBox1.addFruit(new Apple());
        }

        for (int i = 0; i < 4; i++) {
            appleBox2.addFruit(new Apple());
        }

        System.out.println("orangeBox1 weight: " + orangeBox1.getWeight());
        System.out.println("orangeBox2 weight: " + orangeBox2.getWeight());
        System.out.println("appleBox1 weight: " + appleBox1.getWeight());
        System.out.println("appleBox2 weight: " + appleBox2.getWeight());

        System.out.println("Equal weight of orange boxes: " + orangeBox1.compare(orangeBox2));
        System.out.println("Equal weight of apple boxes: " + appleBox1.compare(appleBox2));
        System.out.println("Equal weight of appleBox1 and orangeBox2: " + appleBox1.compare(orangeBox2));

        orangeBox1.putToBox(orangeBox2);
        System.out.println("orangeBox1 to orangeBox2: new weight of orangeBox1 " + orangeBox1.getWeight());
        System.out.println("orangeBox1 to orangeBox2: new weight of orangeBox2 " + orangeBox2.getWeight());

        appleBox2.putToBox(appleBox1);
        System.out.println("appleBox2 to appleBox1: new weight of appleBox1 " + appleBox1.getWeight());
        System.out.println("appleBox2 to appleBox1: new weight of appleBox2 " + appleBox2.getWeight());
    }
}
