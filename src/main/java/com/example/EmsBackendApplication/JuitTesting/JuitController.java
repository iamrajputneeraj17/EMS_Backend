package com.example.EmsBackendApplication.JuitTesting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vivo")
public class JuitController {

    @Autowired
    private VivoService service;

//    @

    @PostMapping("/create")
    ResponseEntity<VivoEntity> createMobile(@RequestBody VivoEntity vivoEntity)
    {
        VivoEntity createdObj = service.createMobile(vivoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObj);

    }

    @GetMapping("/all")
    ResponseEntity<List<VivoEntity>> getAllMobiles() {
        List<VivoEntity> createdObj = service.findAll();
        if (createdObj.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.ok(createdObj);
    }

    @GetMapping("/mobiles/{id}")
    public ResponseEntity<VivoEntity> getById(@PathVariable Long id) {
        VivoEntity mob = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mobile not found"));
        return ResponseEntity.ok(mob);
    }


}
