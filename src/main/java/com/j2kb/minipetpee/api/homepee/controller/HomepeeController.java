package com.j2kb.minipetpee.api.homepee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apis/{homepee-id}")
public class HomepeeController {

    @GetMapping
    public ResponseEntity<String> find() {

        return ResponseEntity.ok("Hello Minipetpee!!!");
    }
}
