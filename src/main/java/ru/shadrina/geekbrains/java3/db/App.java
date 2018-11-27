package ru.shadrina.geekbrains.java3.db;

import java.sql.PreparedStatement;
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

        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS goods (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                    "                   NOT NULL,\n" +
                    "    prodid STRING  UNIQUE,\n" +
                    "    title  STRING,\n" +
                    "    cost   INT\n" +
                    ")");
            System.out.println("Таблица создана");
            stmt.execute("DELETE FROM goods");
            PreparedStatement prstmt = connection.prepareStatement("INSERT INTO goods (prodid, title, cost) \n" +
                    " VALUES (?, ?, ?)");
            connection.setAutoCommit(false);
            for (int i = 1; i <= 10000; i++) {
                prstmt.setString(1, "" + i);
                prstmt.setString(2, "товар" + i);
                prstmt.setInt(3, 10 * i);
                prstmt.addBatch();
            }
            prstmt.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Listener listener = new Listener();
        listener.run();
        disconnect();
    }
}
