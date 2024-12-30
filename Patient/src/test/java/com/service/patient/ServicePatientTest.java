package com.service.patient;


import com.service.patient.entity.Patient;
import com.service.patient.repository.PatientRepository;
import com.service.patient.service.ServicePatient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ServicePatientTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private ServicePatient servicePatient;

    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        patient = new Patient(1L, "Mustapha", "salma", 30, 1234567890);
    }

    @Test
    void testCreatePatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient createdPatient = servicePatient.createPatient(patient);

        assertNotNull(createdPatient);
        assertEquals(patient.getNom(), createdPatient.getNom());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testFindPatientById() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Patient foundPatient = servicePatient.findPatientById(1L);

        assertNotNull(foundPatient);
        assertEquals(patient.getId(), foundPatient.getId());
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(patient));

        List<Patient> allPatients = servicePatient.findAllPatients();

        assertNotNull(allPatients);
        assertEquals(1, allPatients.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testUpdatePatient() {
        Patient updatedPatient = new Patient(1L, "Mustapha", "salma", 32, 987654321);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);

        Patient result = servicePatient.updatePatient(1L, updatedPatient);

        assertNotNull(result);
        assertEquals(updatedPatient.getNom(), result.getNom());
        assertEquals(updatedPatient.getPrenom(), result.getPrenom());
        verify(patientRepository, times(1)).findById(1L);
        verify(patientRepository, times(1)).save(updatedPatient);
    }

    @Test
    void testDeletePatient() {
        doNothing().when(patientRepository).deleteById(1L);

        servicePatient.deletePatient(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetPatientById() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Patient foundPatient = servicePatient.getPatientById(1L);

        assertNotNull(foundPatient);
        assertEquals(patient.getId(), foundPatient.getId());
        verify(patientRepository, times(1)).findById(1L);
    }
}

