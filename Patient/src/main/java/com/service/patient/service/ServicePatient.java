package com.service.patient.service;

import com.service.patient.entity.Patient;
import com.service.patient.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicePatient implements  IServicePatient {

    PatientRepository patientRepository ;
    @Override
    public Patient createPatient(Patient p) {
        return patientRepository.save(p);
    }

    @Override
    public Patient findPatientById(Long id) {
        return patientRepository.findById(id).get();
    }

    @Override
    public List<Patient> findAllPatients() {
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Long id, Patient updatePatient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        existingPatient.setNom(updatePatient.getNom());
        existingPatient.setPrenom(updatePatient.getPrenom());
        existingPatient.setAge(updatePatient.getAge());
        existingPatient.setTel(updatePatient.getTel());
        return patientRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(Long id) {

        patientRepository.deleteById(id);

    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).get();

    }
}
