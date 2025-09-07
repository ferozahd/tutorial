package com.tutorial.validation.controllers;

import com.tutorial.validation.resources.RequestData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping()
    public ResponseEntity<?> sayHello(){
        return ResponseEntity.ok(Map.of("message","Hello from Spring boot"));
    }

    @PostMapping("/test")
    public ResponseEntity<RequestData> testPayload(@RequestBody @Valid RequestData data){
        var data2=new RequestData(data.username(), data.password());
        return ResponseEntity.ok(data2);
    }
}
