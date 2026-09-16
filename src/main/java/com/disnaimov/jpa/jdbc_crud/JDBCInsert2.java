package com.disnaimov.jpa.jdbc_crud;

import com.disnaimov.jpa.Student;

import java.sql.*;

public class JDBCInsert2 {
    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("DB_USER");
    static final String PWD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        Connection connection = null;
        Student student = new Student("Leo", "Farrell", 8.4);

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);
            Statement statement = connection.createStatement();
            String sqlQuery = "insert into students(name, surname, avg_grade) values" + "('"
                    + student.getName() + "', '" + student.getSecondName() + "', " + student.getAvgGrade() + ")" ;

            statement.executeUpdate(sqlQuery);

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