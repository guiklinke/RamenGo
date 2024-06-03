package com.RamenGo.RamenGo.controllers;

import com.RamenGo.RamenGo.domain.Protein;
import com.RamenGo.RamenGo.exceptions.ApiKeyMissingException;
import com.RamenGo.RamenGo.services.ProteinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/proteins")
public class ProteinController {

    @Autowired
    private ProteinService proteinService;

    @GetMapping
    public ResponseEntity<List<Protein>> getAllProteins(
            @RequestHeader(required = false, value = "x-api-key") String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            throw new ApiKeyMissingException();
        }

        List<Protein> proteins = proteinService.getAllProteins();
        return ResponseEntity.ok(proteins);
    }
}
