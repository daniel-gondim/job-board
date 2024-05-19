package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Curriculo;
import com.ads.gynvagas.jobboard.repository.CurriculoRepository;
import com.ads.gynvagas.jobboard.service.CurriculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CurriculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testaObterStatus200AoAdicionarCurriculo() throws Exception {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);
        Curriculo curriculo = new Curriculo();
        curriculo.setNome("João Silva");
        curriculo.setEmail("joao.silva@example.com");
        curriculo.setTelefone("123456789");
        curriculo.setHabilidades(Arrays.asList("Java", "Spring Boot"));
        curriculo.setExperienciaProfissional(Arrays.asList());
        curriculo.setEducacao(Arrays.asList());
        
        Mockito.when(curriculoService.adicionarCurriculo(any(Curriculo.class))).thenReturn(curriculo);

        mockMvc.perform(post("/curriculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curriculo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"));
    }

    @Disabled
    void testaObterTodosOsCurriculos() throws Exception {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);
        Curriculo curriculo1 = new Curriculo();
        curriculo1.setNome("Maria Souza");
        curriculo1.setEmail("maria.souza@example.com");
        curriculo1.setTelefone("987654321");
        curriculo1.setHabilidades(Arrays.asList("Python", "Django"));
        curriculo1.setExperienciaProfissional(Arrays.asList());
        curriculo1.setEducacao(Arrays.asList());
        
        Curriculo curriculo2 = new Curriculo();
        curriculo2.setNome("Carlos Pereira");
        curriculo2.setEmail("carlos.pereira@example.com");
        curriculo2.setTelefone("123456789");
        curriculo2.setHabilidades(Arrays.asList("JavaScript", "React"));
        curriculo2.setExperienciaProfissional(Arrays.asList());
        curriculo2.setEducacao(Arrays.asList());
        
        List<Curriculo> listaDeCurriculos = Arrays.asList(curriculo1, curriculo2);
        Mockito.when(curriculoService.obterTodosOsCurriculos()).thenReturn(listaDeCurriculos);

        mockMvc.perform(get("/curriculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome").value("Maria Souza"))
                .andExpect(jsonPath("$[1].nome").value("Carlos Pereira"));
    }

    @Test
    void editarCurriculo() {
        // Implementar teste para editar currículo
    }

    @Test
    void removerCurriculo() {
        // Implementar teste para remover currículo
    }
}
