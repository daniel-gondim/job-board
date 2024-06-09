package com.ads.gynvagas.jobboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends MongoRepository<User, String> {
    com.ads.gynvagas.jobboard.model.User findByUsername(String username);
}
