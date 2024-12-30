package com.example.medecinservice;

import com.example.medecinservice.controller.MedecinController;
import com.example.medecinservice.entity.Medecin;
import com.example.medecinservice.service.IServiceMedecin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MedecinControllerTest {

    @Mock
    private IServiceMedecin iServiceMedecin;

    @InjectMocks
    private MedecinController medecinController;

    private MockMvc mockMvc;
    private Medecin medecin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(medecinController).build();

        medecin = new Medecin(1L, "Mustapha", "Dr. Salma", "Cardiologist", "Moknine");
    }

    @Test
    void testGetMedecinById() throws Exception {
        when(iServiceMedecin.getMedecinById(1L)).thenReturn(medecin);

        mockMvc.perform(get("/api/medecin/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(medecin.getId()))
                .andExpect(jsonPath("$.nom").value(medecin.getNom()))
                .andExpect(jsonPath("$.prenom").value(medecin.getPrenom()))
                .andExpect(jsonPath("$.specialite").value(medecin.getSpecialite()));

        verify(iServiceMedecin, times(1)).getMedecinById(1L);
    }

    @Test
    void testGetAllMedecins() throws Exception {
        // Given
        when(iServiceMedecin.findAllMedecins()).thenReturn(List.of(medecin));

        mockMvc.perform(get("/api/medecin/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(medecin.getId()))
                .andExpect(jsonPath("$[0].nom").value(medecin.getNom()))
                .andExpect(jsonPath("$[0].prenom").value(medecin.getPrenom()))
                .andExpect(jsonPath("$[0].specialite").value(medecin.getSpecialite()));

        verify(iServiceMedecin, times(1)).findAllMedecins();
    }



    @Test
    void testUpdateMedecin() throws Exception {
        Medecin updatedMedecin = new Medecin(1L, "Dr. Jane", "Doe", "Neurologist", "Dr. Jane");
        when(iServiceMedecin.updateMedecin(eq(1L), any(Medecin.class))).thenReturn(updatedMedecin);

        mockMvc.perform(put("/api/medecin/update/{id}", 1L)
                        .contentType("application/json")
                        .content("{\"nom\": \"Mustapha\", \"prenom\": \"Dr. Salma\", \"specialite\": \"Neurologist\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value(updatedMedecin.getNom()))
                .andExpect(jsonPath("$.prenom").value(updatedMedecin.getPrenom()))
                .andExpect(jsonPath("$.specialite").value(updatedMedecin.getSpecialite()));

        verify(iServiceMedecin, times(1)).updateMedecin(eq(1L), any(Medecin.class));
    }


}
