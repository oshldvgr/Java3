package ru.shadrina.geekbrains.java3.db;

import java.sql.*;

public class DataBase {
    public static Statement stmt;
    public static Connection connection;


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:lesson2.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void search(String goods) {
        try {
            String selectString = "SELECT cost FROM goods where title = " + "\"" + goods + "\"";
            PreparedStatement prstmt = connection.prepareStatement(selectString);
            ResultSet rs = prstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Цена товара " + goods + ": " + rs.getString("cost"));
            } else System.out.println("Товара не обнаружено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void change(String goods, String cost) {
        try {
            String updateString = "UPDATE  goods SET cost = " + "\"" + cost + "\"" + " WHERE title= " + "\"" + goods + "\"";
            PreparedStatement prstmt = connection.prepareStatement(updateString);
            if (prstmt.executeUpdate() > 0) System.out.println("Цена товара была успешно изменена");
            else System.out.println("Такого товара не обнаружено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void sort(String min, String max) {
        try {
            String sortString = "SELECT * FROM goods WHERE  cost > " + "\"" + min + "\"" + " AND cost < " + "\"" + max + "\"" + " ORDER BY cost";
            PreparedStatement prstmt = connection.prepareStatement(sortString);
            ResultSet rs = prstmt.executeQuery();
            if (!rs.next()) System.out.println("Товаров в этом ценовом диапазоне не обнаружено");
            else {
                while (rs.next()) {
                    String prodid = rs.getString("prodid");
                    String title = rs.getString("title");
                    int cost = rs.getInt("cost");
                    System.out.println(prodid + "\t" + title + "\t" + cost);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
