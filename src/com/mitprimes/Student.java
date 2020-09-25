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

        if(lastName.length() > 30 || firstName.length() > 30)
        if(gpa < 0 || gpa > 4) {
            throw new RuntimeException("Invalid input: The names of students should not exceed 30 letters.");
        }
        if(gradYear > 2022 || gradYear < 2018) {
            throw new RuntimeException("Invalid input: The Graduation Year of students should be between 2018 and 2022.");
        }
    }

    public String toString() {
        String result = lastName + ", " + firstName
                + " " + String.format("%.2f", gpa) + " " + gradYear;
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