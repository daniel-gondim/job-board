package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Curriculo;
import com.ads.gynvagas.jobboard.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    public CurriculoService(CurriculoRepository mockRepository) {
        //TODO Auto-generated constructor stub
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
            atualizado.setEmail(curriculo.getEmail());
            atualizado.setTelefone(curriculo.getTelefone());
            atualizado.setHabilidades(curriculo.getHabilidades());
            atualizado.setExperienciaProfissional(curriculo.getExperienciaProfissional());
            atualizado.setEducacao(curriculo.getEducacao());

            return curriculoRepository.save(atualizado);
        } else {
            // Lançar exceção ou lidar com o caso de currículo não encontrado conforme necessário
            return null;
        }
    }

    public void deletarCurriculo(String id) {
        curriculoRepository.deleteById(id);
    }

    public Optional<Curriculo> obterCurriculoPorId(String id) {
        return curriculoRepository.findById(id);
    }
}
