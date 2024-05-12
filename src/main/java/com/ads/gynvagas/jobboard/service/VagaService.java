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

    public Vaga editarVaga(Integer id, Vaga vaga) {
        Vaga vagaExistente = vagaRepository.findById(id).orElseThrow();
        vagaExistente.setNome(vagaExistente.getNome());
        vagaExistente.setDescricao(vagaExistente.getDescricao());
        vagaExistente.setEmpresa(vagaExistente.getEmpresa());
        vagaExistente.setContato(vagaExistente.getContato());
        vagaExistente.setSalario(vagaExistente.getSalario());
        return vagaRepository.save(vaga);
    }

    public void deletaVaga(Integer id) {
        this.vagaRepository.deleteById(id);
    }
}
