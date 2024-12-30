package com.example.medecinservice.repository;

import com.example.medecinservice.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}

