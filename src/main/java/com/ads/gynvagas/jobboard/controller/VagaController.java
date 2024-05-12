package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VagaController {

    @Autowired
    VagaService vagaService;

    @GetMapping("/vagas")
    public List<Vaga> obterTodasAsVagas () {
        return vagaService.obterTodasAsVagas();
    }

    @PostMapping("/vagas")
    public Vaga adicionarVaga(@RequestBody Vaga vaga) {
        return vagaService.adicionarVaga(vaga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> editarVaga(@PathVariable Integer id, @RequestBody Vaga vaga) {
        Vaga vagaAtualizada = vagaService.editarVaga(id, vaga);
        return ResponseEntity.ok(vagaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerVaga(@PathVariable Integer id) {
        vagaService.deletaVaga(id);
        return ResponseEntity.ok("Vaga deleteda com sucesso.");
    }

}
