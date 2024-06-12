package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Curriculo;
import com.ads.gynvagas.jobboard.model.Curriculo.Endereco;
import com.ads.gynvagas.jobboard.model.Curriculo.Experiencia;
import com.ads.gynvagas.jobboard.model.Curriculo.Formacao;
import com.ads.gynvagas.jobboard.service.CurriculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @MockBean
    private CurriculoService curriculoService;

    @Test
    void testaObterStatus200AoAdicionarCurriculo() throws Exception {
        Curriculo curriculo = new Curriculo();
        curriculo.setNome("João Silva");
        curriculo.setSobrenome("Silva");
        curriculo.setEmail("joao.silva@example.com");
        curriculo.setTelefone("123456789");

        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        endereco.setLogradouro("Rua A");
        endereco.setCidade("Cidade B");
        endereco.setUf("UF");

        Experiencia experiencia = new Experiencia();
        experiencia.setEmpresa("Empresa X");
        experiencia.setFuncao("Desenvolvedor");

        Formacao formacao = new Formacao();
        formacao.setCurso("Ciência da Computação");
        formacao.setNivel("Graduação");

        curriculo.setEndereco(endereco);
        curriculo.setExperiencias(Arrays.asList(experiencia));
        curriculo.setFormacoes(Arrays.asList(formacao));

        Mockito.when(curriculoService.adicionarCurriculo(any(Curriculo.class))).thenReturn(curriculo);

        mockMvc.perform(post("/curriculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curriculo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"));
    }

    @Disabled
    void testaObterTodosOsCurriculos() throws Exception {
        Curriculo curriculo1 = new Curriculo();
        curriculo1.setNome("Maria Souza");
        curriculo1.setSobrenome("Souza");
        curriculo1.setEmail("maria.souza@example.com");
        curriculo1.setTelefone("987654321");

        Curriculo curriculo2 = new Curriculo();
        curriculo2.setNome("Carlos Pereira");
        curriculo2.setSobrenome("Pereira");
        curriculo2.setEmail("carlos.pereira@example.com");
        curriculo2.setTelefone("123456789");

        List<Curriculo> listaDeCurriculos = Arrays.asList(curriculo1, curriculo2);
        Mockito.when(curriculoService.obterTodosOsCurriculos()).thenReturn(listaDeCurriculos);

        mockMvc.perform(get("/curriculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome").value("Maria Souza"))
                .andExpect(jsonPath("$[1].nome").value("Carlos Pereira"));
    }

    @Test
    void editarCurriculo() throws Exception {
        Curriculo curriculo = new Curriculo();
        curriculo.setNome("João Silva");
        curriculo.setSobrenome("Silva");
        curriculo.setEmail("joao.silva@example.com");
        curriculo.setTelefone("123456789");

        Curriculo curriculoAtualizado = new Curriculo();
        curriculoAtualizado.setNome("João Atualizado");
        curriculoAtualizado.setSobrenome("Silva");
        curriculoAtualizado.setEmail("joao.atualizado@example.com");
        curriculoAtualizado.setTelefone("987654321");

        Mockito.when(curriculoService.obterCurriculoPorId(any(String.class))).thenReturn(Optional.of(curriculo));
        Mockito.when(curriculoService.editarCurriculo(any(String.class), any(Curriculo.class))).thenReturn(curriculoAtualizado);

        mockMvc.perform(put("/curriculos/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curriculoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Atualizado"))
                .andExpect(jsonPath("$.email").value("joao.atualizado@example.com"));
    }

    @Test
    void removerCurriculo() throws Exception {
        Mockito.doNothing().when(curriculoService).deletarCurriculo("1");

        mockMvc.perform(delete("/curriculos/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Currículo deletado com sucesso."));
    }
}
