package com.example.rdvservice.service;

import com.example.rdvservice.entity.Rdv;
import com.example.rdvservice.repository.RdvRepository;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@EnableFeignClients

public class ServiceRdv implements IServiceRdv{

     public final RdvRepository rdvRepository;


    @Override
    public Rdv getRdvById(Long id) {
        return rdvRepository.findById(id).get();
    }

    @Override
    public Rdv createRdv(Rdv d) {
        Rdv rdv1=rdvRepository.findByMedecinIdAndDateRdv(d.getMedecin().getId(),d.getDateRdv());
        Rdv rdv2=rdvRepository.findByPatientIdAndDateRdv(d.getPatient().getId(),d.getDateRdv());
        if(rdv1==null && rdv2==null)
            return rdvRepository.save(d);
        else
            return null;
    }


    @Override
    public Rdv findRdvById(Long id) {
        return rdvRepository.findById(id).get();
    }

    @Override
    public List<Rdv> findAllRdv() {
        return rdvRepository.findAll();
    }

    @Override
    public Rdv updateRdv(Rdv a) {
        return rdvRepository.save(a);
    }

    @Override
    public void deleteRdv(Long id) {
        rdvRepository.deleteById(id);

    }


}
