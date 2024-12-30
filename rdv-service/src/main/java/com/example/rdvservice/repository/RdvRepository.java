package com.example.rdvservice.repository;

import com.example.rdvservice.entity.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RdvRepository extends JpaRepository<Rdv, Long> {

    Rdv findByPatientIdAndDateRdv(Long id, LocalDateTime ldt);
    Rdv findByMedecinIdAndDateRdv(Long id,LocalDateTime ldt);
}




