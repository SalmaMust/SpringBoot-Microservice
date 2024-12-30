package com.service.patient.service;

import com.service.patient.entity.Patient;

import java.util.List;

public interface IServicePatient {
    public Patient createPatient(Patient p );
    public Patient findPatientById(Long id );
    public List<Patient> findAllPatients();
    public Patient updatePatient(Long id,Patient p);
    public void deletePatient(Long id );

    Patient getPatientById(Long id);
}
