package com.service.patient.controller;

import com.service.patient.entity.Patient;
import com.service.patient.service.IServicePatient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin()
@AllArgsConstructor
@RequestMapping("/api/patient/")
public class PatientController {

    public IServicePatient iServicePatient ;

    @GetMapping("{id}")
    public Patient getById(@PathVariable Long id ){
        return iServicePatient.getPatientById(id);
    }
    @GetMapping("all")
    public List<Patient> getall(){
        return iServicePatient.findAllPatients();
    }

    @GetMapping  ("/getparid/{id}")
    public ResponseEntity getparid (@PathVariable Long id ){
        return new ResponseEntity(iServicePatient.findPatientById(id), HttpStatus.ACCEPTED);   }



    @PostMapping("add")
    public  Patient add (@RequestBody  Patient p ){
        return iServicePatient.createPatient(p)  ;    }


    @DeleteMapping("delete/{id}")
    public  String delete (@PathVariable("id") Long id ){
        iServicePatient.deletePatient(id);
        return "Suppression réussite";
    }

    @PutMapping  ("update/{id}")
    public  Patient update (@PathVariable Long id,@RequestBody Patient p ){
        return iServicePatient.updatePatient(id, p);
    }
}
