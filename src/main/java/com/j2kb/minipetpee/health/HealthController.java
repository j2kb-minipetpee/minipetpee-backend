package com.j2kb.minipetpee.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/aws/health")
    public ResponseEntity<Void> healthCheck() {

        return ResponseEntity.ok().build();
    }
}
