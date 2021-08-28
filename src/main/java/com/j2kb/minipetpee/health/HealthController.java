package com.j2kb.minipetpee.health;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping
public class HealthController {

    @GetMapping("/aws/health")
    public ResponseEntity<Void> healthCheck() {

        return ResponseEntity.ok().build();
    }
}
