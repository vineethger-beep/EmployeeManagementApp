package com.example.EmployeeManagementApp.constants;

public enum Department {

    HR("HR01", "Human Resources"),
    IT("IT01", "Information Technology"),
    SALES("SA01", "Sales Department");

    private final String code;
    private final String description;

    // Constructor (ALWAYS private in enum)
    Department(String code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getter
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}