package com.example.EmployeeManagementApp.constants;

public enum DegreeType {

    B_TECH("B.Tech", "Bachelor of Technology"),
    MSC("M.Sc", "Master of Science"),
    PHD("PhD", "Doctor of Philosophy"),
    CERTIFICATION("CERT", "Professional Certification"),
    M_TECH("M.Tech", "Master of Technology");

    private final String shortName;
    private final String fullName;

    // Constructor (must be private)
    DegreeType(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    // Getters
    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }
}
