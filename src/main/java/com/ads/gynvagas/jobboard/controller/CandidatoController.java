package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Candidato;
import com.ads.gynvagas.jobboard.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CandidatoController {

    @Autowired
    CandidatoService candidatoService;

    @GetMapping("/candidatos")
    public List<Candidato> obterTodosOsCandidatos () { return candidatoService.obterTodosOsCandidatos();}

    @PostMapping("/candidatos")
    public Candidato adicionarCandidato (@RequestBody Candidato candidato) {return candidatoService.adicionarCandidato(candidato);}

    @PutMapping("/candidatos/{id}")
    public ResponseEntity<Candidato> editarCandidato(@PathVariable Integer id, @RequestBody Candidato candidato) {
        Candidato candidatoAtualizado = candidatoService.editarCandidato(id, candidato);
        return ResponseEntity.ok(candidatoAtualizado);
    }
    @DeleteMapping("/candidatos/{id}")
    public ResponseEntity<String> removerCandidato(@PathVariable Integer id) {
        candidatoService.deletaCandidato(id);
        return ResponseEntity.ok("Candidato deletado com sucesso.");
    }

}
