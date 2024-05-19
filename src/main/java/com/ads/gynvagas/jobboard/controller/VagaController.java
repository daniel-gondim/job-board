package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/vagas")  // Adicione um nível de mapeamento base para os endpoints
public class VagaController {

    @Autowired
    VagaService vagaService;

    @GetMapping()
    public List<Vaga> obterTodasAsVagas() {
        return vagaService.obterTodasAsVagas();
    }

    @PostMapping()
    public Vaga adicionarVaga(@RequestBody Vaga vaga) {
        return vagaService.adicionarVaga(vaga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> editarVaga(@PathVariable String id, @RequestBody Vaga vaga) {
        Vaga vagaAtualizada = vagaService.editarVaga(id, vaga);
        return ResponseEntity.ok(vagaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerVaga(@PathVariable String id) {
        vagaService.deletaVaga(id);
        return ResponseEntity.ok("Vaga deleteda com sucesso.");
    }

}
