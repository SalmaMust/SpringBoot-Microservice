package com.example.medecinservice.controller;

import com.example.medecinservice.entity.Medecin;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.medecinservice.service.IServiceMedecin;

import java.util.List;
@RestController
@RequestMapping("/api/medecin/")
@AllArgsConstructor
public class MedecinController {

    public IServiceMedecin iServiceMedecin;


    @GetMapping("{id}")
    public Medecin getById(@PathVariable Long id ){
        return iServiceMedecin.getMedecinById(id);
    }
    @GetMapping("all")
    public List<Medecin> getall(){
        return iServiceMedecin.findAllMedecins();
    }

    @GetMapping  ("/getparid/{id}")
    public ResponseEntity getparid (@PathVariable Long id ){
        return new ResponseEntity(iServiceMedecin.findMedecinById(id), HttpStatus.ACCEPTED);   }



    @PostMapping("add")
    public  Medecin add (@RequestBody Medecin m ){return iServiceMedecin.createMedecin(m)  ;    }


    @DeleteMapping("delete/{id}")
    public  String delete (@PathVariable("id") Long id ){
        iServiceMedecin.deleteMedecin(id);
        return "Suppression réussite ";
    }

    @PutMapping  ("update/{id}")
    public  Medecin update (@PathVariable Long id,@RequestBody Medecin m ){
        return iServiceMedecin.updateMedecin(id, m);
    }
}
