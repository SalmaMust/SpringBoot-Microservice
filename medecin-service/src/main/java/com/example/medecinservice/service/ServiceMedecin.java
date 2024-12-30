package com.example.medecinservice.service;

import com.example.medecinservice.entity.Medecin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.medecinservice.repository.MedecinRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServiceMedecin implements IServiceMedecin{
    private final MedecinRepository medecinRepository;
    @Override
    public Medecin createMedecin(Medecin m) {
        return medecinRepository.save(m);
    }
    @Override
    public Medecin findMedecinById(Long id) {

        return  medecinRepository.findById(id).get();
    }

    @Override
    public List<Medecin> findAllMedecins() {
        return (List<Medecin>) medecinRepository.findAll();
    }

    @Override
    public Medecin updateMedecin(Long id, Medecin  updateMedecin) {
        Medecin existingMedecin = medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medecin not found with id: " + id));

        existingMedecin.setNom(updateMedecin.getNom());
        existingMedecin.setPrenom(updateMedecin.getPrenom());
        existingMedecin.setAdresse(updateMedecin.getAdresse());
        existingMedecin.setSpecialite(updateMedecin.getSpecialite());
        return medecinRepository.save(existingMedecin);

    }

    @Override
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);

    }

    @Override
    public Medecin getMedecinById(Long id) {
        return medecinRepository.findById(id).get();
    }



}
