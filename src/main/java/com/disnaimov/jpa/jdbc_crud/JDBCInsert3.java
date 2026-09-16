package com.disnaimov.jpa.jdbc_crud;

import com.disnaimov.jpa.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCInsert3 {
    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("DB_USER");
    static final String PWD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        Connection connection = null;
        Student student = new Student("Isaak", "Sharp", 9.8);

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students(name, surname, avg_grade) VALUES (?, ?, ?)"
            , Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, student.getName());
            statement.setString(2, student.getSecondName());
            statement.setDouble(3, student.getAvgGrade());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to add student to database");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Failed to create Student ID");
            }

            System.out.println(student);

            statement.close();
            generatedKeys.close();

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