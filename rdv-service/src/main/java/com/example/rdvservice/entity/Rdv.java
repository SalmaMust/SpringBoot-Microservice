package com.example.rdvservice.entity;

import com.example.rdvservice.Model.Medecin;
import com.example.rdvservice.Model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rdv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateRdv;
    private  String etat;

    @Transient
    private Patient patient;
    private  Long patientId ;

    @Transient
    private Medecin medecin;
    private  Long medecinId ;
    @Override
    public String toString() {
        return "Rdv{" +
                "id=" + id +
                ", dateRdv=" + dateRdv +
                ", etat='" + etat + '\'' +
                ", patientId=" + patientId +
                ", patient=" + patient +
                ", medecinId=" + medecinId +

                ", medecin=" + medecin +
                '}';
    }
}
