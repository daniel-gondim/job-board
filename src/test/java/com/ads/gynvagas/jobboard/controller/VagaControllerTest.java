package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.VagaRepository;
import com.ads.gynvagas.jobboard.service.VagaService;
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
class VagaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testaObterStatus200AoAdicionarVaga() throws Exception {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);
        Vaga vaga = new Vaga();
        vaga.setNome("Desenvolvedor Mobile");
        vaga.setDescricao("manutenção de aplicativos android");
        vaga.setEmpresa("Comapnhia A");
        vaga.setContato("12345674");
        vaga.setSalario(7520.09);
        Mockito.when(vagaService.adicionarVaga(any(Vaga.class))).thenReturn(vaga);

        mockMvc.perform(post("/vagas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaga)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Desenvolvedor Mobile"));
    }


    @Disabled
    void testaObterTodasAsVagas() throws Exception {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);
        Vaga vaga1 = new Vaga();
        vaga1.setNome("SDET");
        vaga1.setDescricao("manutenção de aplicativos android");
        vaga1.setEmpresa("Comapnhia B");
        vaga1.setContato("123444444");
        vaga1.setSalario(17520.09);
        Mockito.when(vagaService.adicionarVaga(any(Vaga.class))).thenReturn(vaga1);

        Vaga vaga2 = new Vaga();
        vaga2.setNome("Analista de Requisitos");
        vaga2.setDescricao("cuidar das regras de negócio");
        vaga2.setEmpresa("Comapnhia B");
        vaga2.setContato("123444444");
        vaga2.setSalario(5120.11);
        Mockito.when(vagaService.adicionarVaga(any(Vaga.class))).thenReturn(vaga2);

        List<Vaga> listaDeVagas = List.of();

        listaDeVagas.add(vaga1);
        listaDeVagas.add(vaga2);
        Mockito.when(vagaService.obterTodasAsVagas()).thenReturn(listaDeVagas);

        mockMvc.perform(post("/vagas"))
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome").value("SDET"))
                .andExpect(jsonPath("$[1].nome").value("Desenvolvedor Web"));
    }

    @Test
    void editarVaga() {
    }

    @Test
    void removerVaga() {

    }

}
