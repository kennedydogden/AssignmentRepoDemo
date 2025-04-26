package com.medicationadherence.controller;

import com.medicationadherence.model.Patient;
import com.medicationadherence.model.AdherenceRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * REST CTRL for managing patient-related operations.
 * Handles patient creation, adherence record management, and data retrieval.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow requests from any origin for testing
public class PatientController {
    
    private List<Patient> patients = new ArrayList<>();

    /**
     * Creates a new patient.
     * @param request Contains patient details (firstName, lastName, age, email)
     * @return ResponseEntity containing the created patient or bad request if validation fails
     */
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

    /**
     * Retrieves all patients in the system.
     * @return List of all patients
     */
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patients);
    }

    /**
     * Adds an adherence record for a specific patient.
     * @param id The patient's ID
     * @param request Contains adherence details (medicationName, dosage)
     * @return ResponseEntity with success/failure status
     */
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

    /**
     * Retrieves all adherence records for a specific patient.
     * @param id The patient's ID
     * @return List of adherence records for the patient or error status if patient not found
     */
    @GetMapping("/adherence/{id}")
    public ResponseEntity<?> getAdherenceRecords(@PathVariable int id) {
        try {
            // Find patient by ID
            Patient patient = patients.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

            if (patient == null) {
                return ResponseEntity.notFound().build();
            }

            // Get adherence records for the patient
            List<Map<String, Object>> records = patient.getAdherenceRecords().stream()
                .map(record -> {
                    Map<String, Object> recordMap = new HashMap<>();
                    recordMap.put("date", record.getTimeTaken());
                    recordMap.put("medicationName", record.getMedicationName());
                    recordMap.put("dosage", record.getDosage());
                    return recordMap;
                })
                .collect(Collectors.toList());

            return ResponseEntity.ok(records);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

/**
 * Object for patient creation requests.
 * Contains basic patient information needed to create a new patient.
 */
class PatientRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String email;

    // Getters and setters
    public String getFirstName() { 
        return firstName; 
    }
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }
    public String getLastName() { 
        return lastName; 
    }
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    public int getAge() { 
        return age; 
    }
    public void setAge(int age) { 
        this.age = age; 
    }
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
}

/**
 * Object for adherence record creation requests.
 * Contains medication details needed to create a new adherence record.
 */
class AdherenceRecordRequest {
    private String medicationName;
    private int dosage;

    // Getters and setters
    public String getMedicationName() {
         return medicationName; 
        }
    public void setMedicationName(String medicationName) { 
        this.medicationName = medicationName; }
    public int getDosage() { 
        return dosage; 
    }
    public void setDosage(int dosage) {
        this.dosage = dosage; 
    }
} 