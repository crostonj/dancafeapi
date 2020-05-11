package com.danscafe.siteapi.controller;

import com.danscafe.siteapi.model.JwtResponse;
import org.springframework.http.ResponseEntity;

public class RegistrationController {
    public ResponseEntity<JwtResponse> register(){
        return ResponseEntity.ok(new JwtResponse("user"));
    }
}
