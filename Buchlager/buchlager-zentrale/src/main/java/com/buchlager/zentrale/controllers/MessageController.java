package com.buchlager.zentrale.controllers;

import com.buchlager.core.model.Bestellung;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("messages")
public class MessageController {

    @PostMapping
    synchronized ResponseEntity<Bestellung> getMessage(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Bestellung bestellung = null;
        if(json.isBlank()){
            return ResponseEntity.badRequest().build();
        }else {
            try {
                bestellung = objectMapper.readValue(json, Bestellung.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        System.out.println(bestellung + " ist angekommen");
        return ResponseEntity.ok(bestellung);
    }
}
