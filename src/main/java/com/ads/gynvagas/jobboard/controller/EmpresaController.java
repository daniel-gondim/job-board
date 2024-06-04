package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Empresa;
import com.ads.gynvagas.jobboard.model.Vaga;
import com.ads.gynvagas.jobboard.service.EmpresaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Empresa>> obterEmpresas() {
        List<Empresa> empresas = empresaService.obterEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @PostMapping()
    public Empresa adicionarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.adicionarEmpresa(empresa);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> editarEmpresa(@PathVariable String id, @RequestBody Empresa empresa){
        Empresa empresaAtualizada = empresaService.editarEmpresa(id, empresa);
        return ResponseEntity.ok(empresaAtualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterEmpresaPorId(@PathVariable String id) {
        Empresa empresa = empresaService.obterEmpresaPorId(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa); // Retorna 200 OK e os detalhes da empresa
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se a empresa não for encontrada
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEmpresa(@PathVariable String id) {
        empresaService.deletarEmpresa(id);
        return ResponseEntity.noContent().build();


    }

}