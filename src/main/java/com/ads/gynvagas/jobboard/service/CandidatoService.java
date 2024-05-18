package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Candidato;
import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.repository.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {
    private CandidatoRepository candidatoRepository;

    public CandidatoService(CandidatoRepository candidatoRepository) { this.candidatoRepository = candidatoRepository;}

    public Candidato adicionarCandidato(Candidato candidato) {return candidatoRepository.save(candidato);}

    public List<Candidato> obterTodosOsCandidatos() {return candidatoRepository.findAll();}

    public Candidato editarCandidato(Integer id, Candidato candidato) {
        Candidato candidatoExistente = candidatoRepository.findById(id).orElseThrow();
        candidatoExistente.setNome(candidatoExistente.getNome());
        candidatoExistente.setCpf(candidatoExistente.getCpf());
        candidatoExistente.setEmail(candidatoExistente.getEmail());
        candidatoExistente.setId(candidatoExistente.getId());
        return candidatoRepository.save(candidato);
    }
    public void deletaCandidato(Integer id) {
        this.candidatoRepository.deleteById(id);
    }

}
