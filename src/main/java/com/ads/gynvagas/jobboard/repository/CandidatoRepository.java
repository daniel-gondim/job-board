package com.ads.gynvagas.jobboard.repository;

import com.ads.gynvagas.jobboard.model.Candidato;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidatoRepository extends MongoRepository <Candidato, Integer> {
}
