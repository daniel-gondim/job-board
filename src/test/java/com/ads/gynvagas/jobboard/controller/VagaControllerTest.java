package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.VagaRepository;
import com.ads.gynvagas.jobboard.service.VagaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    void testaObterStatus200AoObterTodasAsVagasD() throws Exception {
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


    @Test
    void adicionarVaga() {
    }

    @Test
    void editarVaga() {
    }

    @Test
    void removerVaga() {

    }

}
