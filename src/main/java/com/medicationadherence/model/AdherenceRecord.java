package com.medicationadherence.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AdherenceRecord {
    private String medicationName;
    private LocalDateTime timeTaken;
    private int dosage;
    private boolean taken;

    public AdherenceRecord(String medicationName, int dosage) {
        if(dosage < 0) {
            throw new IllegalArgumentException("Invalid dosage");
        }

        this.setMedicationName(medicationName);
        this.setTimeTaken(LocalDateTime.now());
        this.setDosage(dosage);
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        if(medicationName == null || !medicationName.matches("[a-zA-Z\\s]+") || medicationName.isEmpty()) {
            throw new IllegalArgumentException("Invalid medication name (only letters and spaces). Data not saved");
        }
        this.medicationName = medicationName;
    }

    public LocalDateTime getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(LocalDateTime timeTaken) {
        this.timeTaken = timeTaken.truncatedTo(ChronoUnit.MINUTES);
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        if(dosage < 0) {
            throw new IllegalArgumentException("Invalid dosage. Dosage not saved");
        }
        this.dosage = dosage;
        taken = dosage != 0;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
} 