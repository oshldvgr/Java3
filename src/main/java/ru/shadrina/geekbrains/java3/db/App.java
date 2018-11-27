package ru.shadrina.geekbrains.java3.db;

import java.sql.SQLException;

import static ru.shadrina.geekbrains.java3.db.DataBase.*;

public class App {
    public static void main(String[] args) {
        try {
            connect();
            System.out.println("Соединение с БД установлено");
        } catch (ClassNotFoundException e) {
            e.getException().printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        createTable();
        clearTable();
        fillInTable();
        Listener listener = new Listener();
        listener.run();
        disconnect();
    }
}
