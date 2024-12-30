package com.example.rdvservice.service;
import com.example.rdvservice.entity.Rdv;
import java.util.List;

public interface IServiceRdv {

    Rdv getRdvById(Long id);
    public Rdv createRdv(Rdv p);
    public  Rdv findRdvById(Long id);
    public List<Rdv> findAllRdv();
    public Rdv updateRdv(Rdv a);
    public void deleteRdv(Long id);




}
