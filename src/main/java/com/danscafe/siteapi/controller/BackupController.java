package com.danscafe.siteapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackupController {

    @GetMapping(value = {"/backup"})
    public String hello(){
        return "You went backwards";
    }
}
