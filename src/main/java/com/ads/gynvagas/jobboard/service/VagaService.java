package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public Vaga editarVaga(int id, Vaga vaga) {
        return vagaRepository.save(vaga);
    }

}
