package br.com.generic.api.genericapi.controller;

import br.com.generic.api.genericapi.model.GenericRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/**/generic")
public class GenericResource {

    @PostMapping
    public ResponseEntity<?> teste(@RequestBody GenericRequest genericRequest){
        return new ResponseEntity<>(genericRequest, OK);
    }


}
