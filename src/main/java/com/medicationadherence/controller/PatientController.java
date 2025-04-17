package com.medicationadherence.controller;

import com.medicationadherence.model.Patient;
import com.medicationadherence.model.AdherenceRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow requests from any origin for testing
public class PatientController {
    
    private List<Patient> patients = new ArrayList<>();

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody PatientRequest request) {
        try {
            Patient patient = new Patient(
                request.getFirstName(),
                request.getLastName(),
                request.getAge(),
                request.getEmail()
            );
            patients.add(patient);
            return ResponseEntity.ok(patient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patients);
    }

    @PostMapping("/patients/{id}/adherence")
    public ResponseEntity<?> addAdherenceRecord(
            @PathVariable int id,
            @RequestBody AdherenceRecordRequest request) {
        try {
            // Find patient by ID
            Patient patient = patients.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

            if (patient == null) {
                return ResponseEntity.notFound().build();
            }

            // Create and add adherence record
            AdherenceRecord record = new AdherenceRecord(
                request.getMedicationName(),
                request.getDosage()
            );
            patient.addAdherenceRecord(record);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

class PatientRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String email;

    // Getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

class AdherenceRecordRequest {
    private String medicationName;
    private int dosage;

    // Getters and setters
    public String getMedicationName() { return medicationName; }
    public void setMedicationName(String medicationName) { this.medicationName = medicationName; }
    public int getDosage() { return dosage; }
    public void setDosage(int dosage) { this.dosage = dosage; }
} 