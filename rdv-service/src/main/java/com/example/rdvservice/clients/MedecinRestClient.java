package com.example.rdvservice.clients;

import com.example.rdvservice.Model.Medecin;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@EnableFeignClients
@FeignClient(name = "medecin-service")
public interface MedecinRestClient {
    @GetMapping("/api/medecin/{id}")
    Medecin getMedecinById(@PathVariable Long id);
}

