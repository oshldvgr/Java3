package ru.shadrina.geekbrains.java3.db;

import java.sql.*;

public class DataBase {
    public static Connection connection;


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:lesson2.db");
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS goods (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                    "                   NOT NULL,\n" +
                    "    prodid STRING  UNIQUE,\n" +
                    "    title  STRING,\n" +
                    "    cost   INT\n" +
                    ")");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM goods");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillInTable() {
        try (PreparedStatement prstmt = connection.prepareStatement("INSERT INTO goods (prodid, title, cost) \n" +
                " VALUES (?, ?, ?)")) {
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
    }

    public static void search(String goods) {
        try (PreparedStatement prstmt = connection.prepareStatement("SELECT cost FROM goods where title = ?")) {
            prstmt.setString(1, goods);
            ResultSet rs = prstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Цена товара " + goods + ": " + rs.getString("cost"));
            } else System.out.println("Товара не обнаружено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void change(String goods, String cost) {
        try (PreparedStatement prstmt = connection.prepareStatement("UPDATE  goods SET cost = ?  WHERE title= ?")) {
            prstmt.setString(1, cost);
            prstmt.setString(2, goods);
            if (prstmt.executeUpdate() > 0) System.out.println("Цена товара была успешно изменена");
            else System.out.println("Такого товара не обнаружено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void sort(String min, String max) {
        try (PreparedStatement prstmt = connection.prepareStatement("SELECT * FROM goods " +
                "WHERE  cost >= ? AND cost <= ? ORDER BY cost")) {
            prstmt.setString(1, min);
            prstmt.setString(2, max);
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                String prodid = rs.getString("prodid");
                String title = rs.getString("title");
                int cost = rs.getInt("cost");
                System.out.println(prodid + "\t" + title + "\t" + cost);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
