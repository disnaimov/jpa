package com.disnaimov.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelect {
    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("DB_USER");
    static final String PWD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM students WHERE avg_grade > ?");

            statement.setDouble(1, 9.0);

            ResultSet resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSecondName(resultSet.getString("surname"));
                student.setAvgGrade(resultSet.getDouble("avg_grade"));

                students.add(student);
            }

            System.out.println(students);
            statement.close();
            resultSet.close();

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
