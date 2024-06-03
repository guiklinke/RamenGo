package com.RamenGo.RamenGo.controllers;

import com.RamenGo.RamenGo.domain.Broth;
import com.RamenGo.RamenGo.exceptions.ApiKeyMissingException;
import com.RamenGo.RamenGo.services.BrothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/broths")
public class BrothController {

    @Autowired
    private BrothService brothService;

    @GetMapping
    public ResponseEntity<List<Broth>> getAllBroths(
            @RequestHeader(required = false, value = "x-api-key") String apiKey ){

        if (apiKey == null || apiKey.isEmpty()) {
            throw new ApiKeyMissingException();
        }

        List<Broth> broths = brothService.getAllBroths();

        return ResponseEntity.status(HttpStatus.OK).body(broths);
    }
}
