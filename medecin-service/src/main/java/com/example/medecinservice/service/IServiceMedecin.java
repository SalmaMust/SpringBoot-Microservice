package com.example.medecinservice.service;

import com.example.medecinservice.entity.Medecin;

import java.util.List;

public interface IServiceMedecin {

    public Medecin createMedecin(Medecin m );
    public Medecin findMedecinById(Long id );
    public List<Medecin> findAllMedecins();
    public Medecin updateMedecin(Long id,Medecin m);
    public void deleteMedecin(Long id );
    Medecin getMedecinById(Long id);


}
