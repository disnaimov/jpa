package com.disnaimov.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCUpdate {
    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("DB_USER");
    static final String PWD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name");
            String enteredName = scanner.nextLine();

            // Подвержен SQL инъекциям
            /*Statement statement = connection.createStatement();
            String sqlQuery = "UPDATE students SET avg_grade = 5.2 " +
                    "WHERE name = '" + enteredName + "'";

            statement.executeUpdate(sqlQuery);

            statement.close();*/

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE students SET avg_grade = 7.5 WHERE name = ?");
            statement.setString(1, enteredName);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
