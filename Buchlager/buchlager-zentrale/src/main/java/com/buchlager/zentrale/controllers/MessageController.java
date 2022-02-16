package com.buchlager.zentrale.controllers;

import com.buchlager.core.model.Bestellung;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("messages")
public class MessageController {

    @PostMapping
    synchronized ResponseEntity<Bestellung> getMessage(@RequestBody Bestellung bestellung){
        System.out.println(bestellung + " ist angekommen");
        return ResponseEntity.ok(bestellung);
    }
}
