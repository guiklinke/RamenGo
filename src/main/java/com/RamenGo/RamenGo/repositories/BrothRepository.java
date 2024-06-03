package com.RamenGo.RamenGo.repositories;


import com.RamenGo.RamenGo.domain.Broth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrothRepository extends MongoRepository<Broth, String> {
}