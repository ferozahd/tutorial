package com.tutorial.validation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@Validated
public class UserController {
    private Logger log=Logger.getLogger(UserController.class.getName());
    @PostMapping(value = "/validate", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<RequestData> validateChecker(@RequestBody @Valid  RequestData data) {
        log.info("this is working");
        return ResponseEntity.ok(data);
    }
}
