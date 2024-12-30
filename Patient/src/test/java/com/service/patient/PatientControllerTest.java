package com.service.patient;

import com.service.patient.controller.PatientController;
import com.service.patient.entity.Patient;
import com.service.patient.service.IServicePatient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    private IServicePatient iServicePatient;

    @InjectMocks
    private PatientController patientController;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setNom("Mustapha");
        patient.setPrenom("salma");
        patient.setAge(30);
        patient.setTel(1234567890);
    }

    @Test
    void testGetById() {
        Long id = 1L;
        when(iServicePatient.getPatientById(id)).thenReturn(patient);


        Patient result = patientController.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Mustapha", result.getNom());
        assertEquals("salma", result.getPrenom());
        verify(iServicePatient, times(1)).getPatientById(id);
    }

    @Test
    void testGetAllPatients() {
        List<Patient> patients = List.of(patient);
        when(iServicePatient.findAllPatients()).thenReturn(patients);

        List<Patient> result = patientController.getall();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mustapha", result.get(0).getNom());
        assertEquals("salma", result.get(0).getPrenom());
        verify(iServicePatient, times(1)).findAllPatients();
    }

    @Test
    void testGetParId() {
        Long id = 1L;
        when(iServicePatient.findPatientById(id)).thenReturn(patient);

        ResponseEntity result = patientController.getparid(id);

        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody() instanceof Patient);
        Patient resultPatient = (Patient) result.getBody();
        assertEquals(id, resultPatient.getId());
        assertEquals("Mustapha", resultPatient.getNom());
        assertEquals("salma", resultPatient.getPrenom());
        verify(iServicePatient, times(1)).findPatientById(id);
    }

    @Test
    void testAddPatient() {
        when(iServicePatient.createPatient(patient)).thenReturn(patient);

        Patient result = patientController.add(patient);

        assertNotNull(result);
        assertEquals("Mustapha", result.getNom());
        assertEquals("salma", result.getPrenom());
        verify(iServicePatient, times(1)).createPatient(patient);
    }

    @Test
    void testDeletePatient() {
        Long id = 1L;

        String result = patientController.delete(id);

        assertEquals("Suppression réussite", result);
        verify(iServicePatient, times(1)).deletePatient(id);
    }

    @Test
    void testUpdatePatient() {
        Long id = 1L;
        Patient updatedPatient = new Patient();
        updatedPatient.setId(id);
        updatedPatient.setNom("Mustapha");
        updatedPatient.setPrenom("Salma");
        updatedPatient.setAge(28);
        updatedPatient.setTel(987654321);

        when(iServicePatient.updatePatient(id, updatedPatient)).thenReturn(updatedPatient);
        Patient result = patientController.update(id, updatedPatient);

        assertNotNull(result);
        assertEquals("Mustapha", result.getNom());
        assertEquals("salma", result.getPrenom());
        verify(iServicePatient, times(1)).updatePatient(id, updatedPatient);
    }
}
