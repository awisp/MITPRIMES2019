package com.mitprimes;

public class Student {
    String lastName;
    String firstName;
    float gpa;
    int gradYear;

    public Student(String lastName, String firstName, float gpa, int gradYear) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gpa = gpa;
        this.gradYear = gradYear;
    }

    public String toString() {
        String result = lastName + ", " + firstName
                + " " + gpa + " " + gradYear;
        return result;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public float getGpa() {
        return gpa;
    }

    public int getGradYear() {
        return gradYear;
    }
}