package com.ads.gynvagas.jobboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.gynvagas.jobboard.model.Curriculo;
import com.ads.gynvagas.jobboard.service.CurriculoService;

@RestController
@RequestMapping("/curriculos")  // Atualize o nível de mapeamento base para 'curriculos'
public class CurriculoController {

    @Autowired
    CurriculoService curriculoService;

    @GetMapping()
    public List<Curriculo> obterTodosOsCurriculos() {
        return curriculoService.obterTodosOsCurriculos();
    }

    @PostMapping()
    public Curriculo adicionarCurriculo(@RequestBody Curriculo curriculo) {
        return curriculoService.adicionarCurriculo(curriculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curriculo> editarCurriculo(@PathVariable String id, @RequestBody Curriculo curriculo) {
        Curriculo curriculoAtualizado = curriculoService.editarCurriculo(id, curriculo);
        return ResponseEntity.ok(curriculoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerCurriculo(@PathVariable String id) {
        curriculoService.deletarCurriculo(id);
        return ResponseEntity.ok("Currículo deletado com sucesso.");
    }
}
