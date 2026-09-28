package com.disnaimov.jpa.relationships.one_to_one;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class OneToOneUni {

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

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Student student1 = new Student("Leo", "Pharell", 9.4);
            Passport passport1 = new Passport("leo.pharel@yahoo.com", 178, "black");

            student1.setPassport(passport1);

            entityManager.persist(passport1);
            entityManager.persist(student1);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
                factory.close();
            }
        }
    }
}
