package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.VagaRepository;

import java.util.List;

public class VagaService {

    private VagaRepository vagaRepository;

    public VagaService(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    public Vaga adicionarVaga(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    public List<Vaga> obterTodasAsVagas() {
        return vagaRepository.findAll();
    }
}
