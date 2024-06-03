package com.RamenGo.RamenGo.services;

import com.RamenGo.RamenGo.domain.Protein;
import com.RamenGo.RamenGo.repositories.ProteinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProteinService {
    @Autowired
    ProteinRepository proteinRepository;

    public List<Protein> getAllProteins(){

        return this.proteinRepository.findAll();
    }

}
