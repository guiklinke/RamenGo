package com.RamenGo.RamenGo.services;

import com.RamenGo.RamenGo.domain.Broth;
import com.RamenGo.RamenGo.repositories.BrothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrothService {
    @Autowired
    BrothRepository brothRepository;

    public List<Broth> getAllBroths(){

        return this.brothRepository.findAll();
    }
}
