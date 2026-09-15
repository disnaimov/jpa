package com.disnaimov.jpa;

public class Employee {
    private Long id;
    private String name;
    private String secondName;
    private Double avgGrade;

    public Employee() {
    }

    public Employee(String name, String secondName, Double avgGrade) {
        this.name = name;
        this.secondName = secondName;
        this.avgGrade = avgGrade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", avgGrade=" + avgGrade +
                '}';
    }
}
