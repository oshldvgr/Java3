package ru.shadrina.geekbrains.java3.db;

import java.util.Scanner;

public class Listener implements Runnable {
    private Scanner message = new Scanner(System.in);

    @Override
    public void run() {
        while (message.hasNext()) {
            String userMessage = message.nextLine();
            String[] words = userMessage.split("\\s");
            switch (words[0]) {
                case "цена":
                    DataBase.search(words[1]);
                    break;
                case "сменитьцену":
                    DataBase.change(words[1], words[2]);
                    break;
                case "товарыпоцене":
                    DataBase.sort(words[1], words[2]);
                    break;
                case "выход":
                    return;
            }
        }
    }
}
