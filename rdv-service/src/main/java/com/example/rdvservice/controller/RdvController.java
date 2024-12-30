package com.example.rdvservice.controller;

import com.example.rdvservice.Model.Medecin;
import com.example.rdvservice.Model.Patient;
import com.example.rdvservice.clients.MedecinRestClient;
import com.example.rdvservice.clients.PatientRestClient;
import com.example.rdvservice.entity.Rdv;
import com.example.rdvservice.service.IServiceRdv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/rdv")
@RequiredArgsConstructor
public class RdvController {
    public final  IServiceRdv iServiceRdv;
    public final  PatientRestClient patientRestClient ;
    public final  MedecinRestClient medecinRestClient ;


    @GetMapping("/{id}")
    public Rdv getRdvById(@PathVariable Long id) {
        Rdv rdv = iServiceRdv.getRdvById(id);
        Patient patient = patientRestClient.getPatientById(rdv.getPatientId());
        Medecin medecin = medecinRestClient.getMedecinById(rdv.getMedecinId());
        rdv.setPatient(patient);
        rdv.setMedecin(medecin);
        return rdv;
    }



    @PostMapping("/add")
    public ResponseEntity<Object> addRdv(@RequestBody Rdv d) {
        System.out.println("Received RDV: " + d);
        if (d.getMedecin() == null) {
            return new ResponseEntity<>("Medecin is null", HttpStatus.BAD_REQUEST);
        }

        Rdv dr = iServiceRdv.createRdv(d);
        if (dr != null) {
            return new ResponseEntity<>(dr, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Le RDV est déjà réservé.", HttpStatus.CONFLICT);
        }
    }



    @PutMapping("/update")
    public ResponseEntity<Rdv>updateRdv(@RequestBody Rdv d){
        Rdv dr = iServiceRdv.updateRdv(d);
        return ResponseEntity.ok(dr);
    }
    @DeleteMapping("/delete/{id}")
    public void  deleteRdv(@PathVariable("id") Long id){
        iServiceRdv.deleteRdv(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rdv>> findAll() {
        return ResponseEntity.ok(iServiceRdv.findAllRdv());
    }
}
