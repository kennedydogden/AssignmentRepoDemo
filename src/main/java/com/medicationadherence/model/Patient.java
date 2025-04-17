package com.medicationadherence.model;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Patient {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private ArrayList<AdherenceRecord> adherenceRecords = new ArrayList<>();
    private int missedDoses = 0;
    private double adherencePercentage;
    private int missedDoseNotis;
    private int id;
    private static int nextId = 1;

    public Patient(String firstName, String lastName, int age, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setEmail(email);
        this.id = nextId++;
    }

    public void addAdherenceRecord(AdherenceRecord adherenceRecord) {
        adherenceRecords.add(adherenceRecord);
        if(!adherenceRecord.isTaken()) {
            missedDoses++;
        }
        if(missedDoses >= 3) {
            System.out.println("DOSE ADHERENCE ALERT");
            missedDoseNotis++;
        }
        adherencePercentage = (double)(adherenceRecords.size()-missedDoses)/adherenceRecords.size()*100;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        if(firstName.isEmpty() || !firstName.matches("^[a-zA-Z'-]{1,50}$")) {
            throw new IllegalArgumentException("Invalid first name");
        }
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        if(lastName.isEmpty() || !lastName.matches("^[a-zA-Z'-]{1,50}$")) {
            throw new IllegalArgumentException("Invalid last name");
        }
        this.lastName = lastName;
    }

    public int getAge() { return age; }
    public void setAge(int age) {
        if(age < 0) {
            throw new IllegalArgumentException("Invalid age");
        }
        this.age = age;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if(email == null || email.isEmpty() || !email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public ArrayList<AdherenceRecord> getAdherenceRecords() { return adherenceRecords; }
    public int getMissedDoses() { return missedDoses; }
    public double getAdherencePercentage() { return adherencePercentage; }
    public int getMissedDoseNotis() { return missedDoseNotis; }
    public int getId() { return id; }
} 