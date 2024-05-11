package com.ads.gynvagas.jobboard.repository;

import com.ads.gynvagas.jobboard.model.Vaga;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VagaRepository extends MongoRepository <Vaga, Integer> {
}
