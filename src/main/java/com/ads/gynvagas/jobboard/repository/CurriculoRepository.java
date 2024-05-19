package com.ads.gynvagas.jobboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ads.gynvagas.jobboard.model.Curriculo;

public interface CurriculoRepository extends MongoRepository<Curriculo, String> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário.
}

