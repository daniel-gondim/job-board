package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.VagaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VagaServiceTest {

    @Test
    void testaAdicionarVaga() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);

        Vaga vaga = new Vaga();
        vaga.setNome("Desenvolvedor Java");
        vaga.setDescricao("Desenvolvimento de aplicações Java");
        vaga.setEmpresa("Empresa XYZ");
        vaga.setContato("contato@empresa.com");
        vaga.setSalario(5000.0);

        Mockito.when(mockRepository.save(vaga)).thenReturn(vaga);

        Vaga vagaSalva = vagaService.adicionarVaga(vaga);

        vaga.setNome("Desenvolvedor Java");
        vaga.setDescricao("Desenvolvimento de aplicações Java");
        vaga.setEmpresa("Empresa XYZ");
        vaga.setContato("contato@empresa.com");
        vaga.setSalario(5000.0);

        assertNotNull(vagaSalva);
        assertEquals(vaga.getNome(), vagaSalva.getNome());
        assertEquals(vaga.getDescricao(), vagaSalva.getDescricao());
        assertEquals(vaga.getEmpresa(), vagaSalva.getEmpresa());
        assertEquals(vaga.getContato(), vagaSalva.getContato());
        assertEquals(vaga.getSalario(), vagaSalva.getSalario());
    }

    @Test
    void testaObterTodasAsVagas() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);


        Vaga vaga1 = new Vaga();
        vaga1.setNome("Desenvolvedor Java");
        vaga1.setDescricao("Desenvolvimento de aplicações Java");
        vaga1.setEmpresa("Empresa XYZ");
        vaga1.setContato("contato@empresa.com");
        vaga1.setSalario(5000.0);

        Vaga vaga2 = new Vaga();
        vaga2.setNome("Desenvolvedor Front-end");
        vaga2.setDescricao("Desenvolvimento de interfaces de usuário");
        vaga2.setEmpresa("Empresa ABC");
        vaga2.setContato("contato@empresaabc.com");
        vaga2.setSalario(4500.0);

        Mockito.when(mockRepository.save(vaga1)).thenReturn(vaga1);
        Mockito.when(mockRepository.save(vaga2)).thenReturn(vaga2);

        vagaService.adicionarVaga(vaga1);
        vagaService.adicionarVaga(vaga2);

        List<Vaga> listaDeVagas = new ArrayList<>();
        Mockito.when(mockRepository.findAll()).thenReturn(listaDeVagas);
        listaDeVagas.add(vaga1);
        listaDeVagas.add(vaga2);

        assertNotNull(listaDeVagas);
        assertEquals(2, listaDeVagas.size());
        assertTrue(listaDeVagas.contains(vaga1));
        assertTrue(listaDeVagas.contains(vaga2));
    }

    @Test
    void testaFalhaAoAdicionarVaga() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);
        Vaga vaga = new Vaga();
        Mockito.when(mockRepository.save(vaga)).thenThrow(RuntimeException.class);
        assertThrows(Exception.class, () -> vagaService.adicionarVaga(vaga));
    }

    @Test
    void testaObterListaVazia() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);

        Mockito.when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        List<Vaga> listaDeVagas = vagaService.obterTodasAsVagas();

        assertTrue(listaDeVagas.isEmpty());
        assertNotNull(listaDeVagas);
    }

    @Test
    void testaAtualizarVaga() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);
        Vaga vagaExistente = new Vaga();
        vagaExistente.setId("1");
        vagaExistente.setNome("Desenvolvedor .Net");
        vagaExistente.setDescricao("Desenvolvimento de aplicações");
        vagaExistente.setEmpresa("Empresa ABC");
        vagaExistente.setContato("contatoabc@empresa.com");
        vagaExistente.setSalario(5678.9);

        Vaga vagaAtualizada = new Vaga();
        vagaAtualizada.setId("1");
        vagaAtualizada.setNome("Desenvolvedor Python");
        vagaAtualizada.setDescricao("Machine Learning Python");

        Mockito.when(mockRepository.findById(("1"))).thenReturn(Optional.of(vagaExistente));
        Mockito.when(mockRepository.save(vagaAtualizada)).thenReturn(vagaAtualizada);

        Vaga resultado = vagaService.editarVaga("1", vagaAtualizada);

        assertNotNull(resultado);
        assertEquals(vagaAtualizada.getNome(), resultado.getNome());
    }

    @Test
    void testaAtualizarVagaNãoExistente() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);

        Vaga vagaExistente = new Vaga();
        vagaExistente.setId("1");
        vagaExistente.setNome("Desenvolvedor .Net");
        vagaExistente.setDescricao("Desenvolvimento de aplicações");
        vagaExistente.setEmpresa("Empresa ABC");
        vagaExistente.setContato("contatoabc@empresa.com");
        vagaExistente.setSalario(5678.9);

        Vaga vagaAtualizada = new Vaga();
        vagaAtualizada.setId("1");
        vagaAtualizada.setNome("Desenvolvedor Python");
        vagaAtualizada.setDescricao("Machine Learning Python");

        Mockito.when(mockRepository.findById(("2"))).thenReturn(Optional.empty());
        Mockito.when(mockRepository.save(vagaAtualizada)).thenReturn(vagaAtualizada);
        assertThrows(NoSuchElementException.class, () -> vagaService.editarVaga("2", vagaAtualizada));
    }
}