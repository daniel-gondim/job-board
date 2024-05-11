package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.VagaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class VagaServiceTest {

    @Test
    void adicionarVaga() {
        VagaRepository mockRepository = Mockito.mock(VagaRepository.class);
        VagaService vagaService = new VagaService(mockRepository);

        Vaga vaga = new Vaga();
        Mockito.when(mockRepository.save(vaga)).thenReturn(vaga);

        Vaga vagaSalva = vagaService.adicionarVaga(vaga);

        assertEquals(vaga, vagaSalva);
    }

    @Test
    void obterTodasAsVagas() {
    }
}