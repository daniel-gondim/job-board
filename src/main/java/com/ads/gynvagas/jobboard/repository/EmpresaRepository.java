package com.ads.gynvagas.jobboard.repository;

import com.ads.gynvagas.jobboard.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
}
