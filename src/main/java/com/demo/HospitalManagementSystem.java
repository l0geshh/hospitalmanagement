package com.demo;

import java.util.HashMap;
import java.util.Map;

public class HospitalManagementSystem {
    private Map<Integer, String> patients = new HashMap<>();
    private int patientIdCounter = 1;

    // Admit a new patient
    public int admitPatient(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be empty");
        }
        int id = patientIdCounter++;
        patients.put(id, name);
        System.out.println("Admitted patient: " + name + " (ID: " + id + ")");
        return id;
    }

    // Discharge a patient
    public boolean dischargePatient(int id) {
        if (patients.containsKey(id)) {
            String name = patients.remove(id);
            System.out.println("Discharged patient: " + name + " (ID: " + id + ")");
            return true;
        }
        System.out.println("Patient ID " + id + " not found.");
        return false;
    }

    // Get total admitted patients
    public int getAdmittedPatientCount() {
        return patients.size();
    }

    // Get patient by ID
    public String getPatientName(int id) {
        return patients.get(id);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Hospital Management System Started ===");
        HospitalManagementSystem hms = new HospitalManagementSystem();
        
        int p1 = hms.admitPatient("John Doe");
        int p2 = hms.admitPatient("Jane Smith");
        
        System.out.println("Total Admitted Patients: " + hms.getAdmittedPatientCount());
        
        System.out.println("\n--- Processing Discharges ---");
        hms.dischargePatient(p1);
        
        System.out.println("Total Admitted Patients remaining: " + hms.getAdmittedPatientCount());
    }
}
