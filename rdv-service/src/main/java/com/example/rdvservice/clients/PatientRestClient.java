package com.example.rdvservice.clients;

import com.example.rdvservice.Model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "patient")
public interface PatientRestClient {

    @GetMapping("/api/patient/{id}")
    Patient getPatientById(@PathVariable Long id);



}
