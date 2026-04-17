package com.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalManagementSystemTest {

    private HospitalManagementSystem hms;

    @BeforeEach
    public void setUp() {
        // This runs before every test to give us a fresh system
        hms = new HospitalManagementSystem();
    }

    @Test
    public void testAdmitPatient() {
        int id = hms.admitPatient("Alice");
        assertEquals(1, hms.getAdmittedPatientCount(), "Patient count should be 1 after admitting");
        assertEquals("Alice", hms.getPatientName(id), "Patient name should match");
    }

    @Test
    public void testDischargePatient() {
        int id = hms.admitPatient("Bob");
        boolean isDischarged = hms.dischargePatient(id);
        
        assertTrue(isDischarged, "Patient should be successfully discharged");
        assertEquals(0, hms.getAdmittedPatientCount(), "Patient count should be 0 after discharge");
    }

    @Test
    public void testDischargeNonExistentPatient() {
        boolean isDischarged = hms.dischargePatient(999);
        assertFalse(isDischarged, "Discharging non-existent patient should return false");
    }

    @Test
    public void testAdmitPatientWithEmptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            hms.admitPatient("");
        });
        assertEquals("Patient name cannot be empty", exception.getMessage());
    }
}
