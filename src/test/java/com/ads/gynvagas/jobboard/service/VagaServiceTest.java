package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.VagaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VagaServiceTest {

    @Test
    void testaAdicionarVaga() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);

        Vaga vaga = new Vaga();
        Mockito.when(mockRepository.save(vaga)).thenReturn(vaga);

        Vaga vagaSalva = vagaService.adicionarVaga(vaga);

        assertEquals(vaga, vagaSalva);
    }

    @Test
    void testaObterTodasAsVagas() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);


        Vaga vaga1 = new Vaga();
        Vaga vaga2 = new Vaga();
        Vaga vaga3 = new Vaga();

        Mockito.when(mockRepository.save(vaga1)).thenReturn(vaga1);
        Mockito.when(mockRepository.save(vaga2)).thenReturn(vaga2);
        Mockito.when(mockRepository.save(vaga3)).thenReturn(vaga3);

        vagaService.adicionarVaga(vaga1);
        vagaService.adicionarVaga(vaga2);
        vagaService.adicionarVaga(vaga3);

        List<Vaga> listaDeVagas = new ArrayList<>();
        listaDeVagas.add(vaga1);
        listaDeVagas.add(vaga1);
        listaDeVagas.add(vaga1);

        assertEquals(true, listaDeVagas.contains(vaga1));
        assertEquals(true, listaDeVagas.contains(vaga2));
        assertEquals(true, listaDeVagas.contains(vaga3));
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
    }
}