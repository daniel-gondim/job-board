package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Curriculo;
import com.ads.gynvagas.jobboard.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CurriculoService {

    private final CurriculoRepository curriculoRepository;

    @Autowired
    public CurriculoService(CurriculoRepository curriculoRepository) {
        this.curriculoRepository = curriculoRepository;
    }

    public List<Curriculo> obterTodosOsCurriculos() {
        return curriculoRepository.findAll();
    }

    public Curriculo adicionarCurriculo(Curriculo curriculo) {
        return curriculoRepository.save(curriculo);
    }

    public Curriculo editarCurriculo(String id, Curriculo curriculo) {
        Optional<Curriculo> curriculoExistente = curriculoRepository.findById(id);

        if (curriculoExistente.isPresent()) {
            Curriculo atualizado = curriculoExistente.get();
            atualizado.setNome(curriculo.getNome());
            atualizado.setSobrenome(curriculo.getSobrenome());
            atualizado.setEmail(curriculo.getEmail());
            atualizado.setTelefone(curriculo.getTelefone());
            atualizado.setEndereco(curriculo.getEndereco());
            atualizado.setExperiencias(curriculo.getExperiencias());
            atualizado.setFormacoes(curriculo.getFormacoes());
            return curriculoRepository.save(atualizado);
        } else {
            throw new NoSuchElementException("Currículo não encontrado com o id: " + id);
        }
    }

    public void deletarCurriculo(String id) {
        Optional<Curriculo> curriculoExistente = curriculoRepository.findById(id);
        if (curriculoExistente.isPresent()) {
            curriculoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Currículo não encontrado com o id: " + id);
        }
    }

    public Optional<Curriculo> obterCurriculoPorId(String id) {
        return curriculoRepository.findById(id);
    }
}
