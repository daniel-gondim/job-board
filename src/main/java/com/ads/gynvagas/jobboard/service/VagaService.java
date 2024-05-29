package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Vaga editarVaga(String id, Vaga vaga) {
        Vaga vagaExistente = vagaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Vaga não encontrada com o ID: " + id));
        vagaExistente.setNome(vaga.getNome());
        vagaExistente.setDescricao(vaga.getDescricao());
        vagaExistente.setEmpresa(vaga.getEmpresa());
        vagaExistente.setContato(vaga.getContato());
        vagaExistente.setSalario(vaga.getSalario());
        return vagaRepository.save(vaga);
    }

    public Vaga obterVagaPorId(String id) {
        return vagaRepository.findById(id).orElse(null);
    }

    public void deletaVaga(String id) {
        this.vagaRepository.deleteById(id);
    }
}
