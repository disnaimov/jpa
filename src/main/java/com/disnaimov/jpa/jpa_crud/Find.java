package com.disnaimov.jpa.jpa_crud;

import com.disnaimov.jpa.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Find {
    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("DB_USER");
    static final String PWD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        Map<String, String> envProperties = new HashMap<>();

        if (DB_URL != null) {
            envProperties.put("jakarta.persistence.jdbc.url", DB_URL);
        }
        if (USER != null) {
            envProperties.put("jakarta.persistence.jdbc.user", USER);
        }
        if (PWD != null) {
            envProperties.put("jakarta.persistence.jdbc.password", PWD);
        }

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course", envProperties);
        EntityManager entityManager = factory.createEntityManager();

        Student student = null;

        try {
            student = entityManager.find(Student.class, 3L);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            factory.close();
        }
        System.out.println(student);
    }
}
