package com.RamenGo.RamenGo.repositories;

import com.RamenGo.RamenGo.domain.Protein;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProteinRepository extends MongoRepository<Protein, String> {
}
