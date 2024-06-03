package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Empresa;
import com.ads.gynvagas.jobboard.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa adicionarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> obterEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa editarEmpresa(String id, Empresa empresa) {
        // Busca a empresa pelo ID
        Empresa empresaExistente = empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com o ID: " + id + " não encontrada."));

        // Atualiza apenas os campos não nulos da empresa existente
        if (empresa.getNome() != null) {
            empresaExistente.setNome(empresa.getNome());
        }
        if (empresa.getEndereco() != null) {
            empresaExistente.setEndereco(empresa.getEndereco());
        }
        if (empresa.getTelefone() != null) {
            empresaExistente.setTelefone(empresa.getTelefone());
        }
        if (empresa.getEmail() != null) {
            empresaExistente.setEmail(empresa.getEmail());
        }

        // Salva e retorna a empresa atualizada
        return empresaRepository.save(empresaExistente);
    }

    public void deletarEmpresa(String id) {
        this.empresaRepository.deleteById(id);
    }
}
