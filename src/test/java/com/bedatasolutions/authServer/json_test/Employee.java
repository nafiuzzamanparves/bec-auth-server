package com.bedatasolutions.authServer.json_test;

import java.io.Serializable;

// Step 1: Create a serializable class
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + '}';
    }
}