package com.ads.gynvagas.jobboard.controller;

import com.ads.gynvagas.jobboard.model.Empresa;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEmpresa(@PathVariable String id) {
        empresaService.deletarEmpresa(id);
        return ResponseEntity.noContent().build();


    }

}