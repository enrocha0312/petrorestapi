package com.mindsim.petroapi.controllers;

import com.mindsim.petroapi.services.VariavelService;
import com.mindsim.petroapi.shared.VariavelRequest;
import com.mindsim.petroapi.shared.VariavelResponse;
import com.mindsim.petroapi.shared.dto.VariavelDTO;
import com.mindsim.petroapi.shared.wrappers.VariavelWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("petroapi")
public class VariavelController {
    @Autowired
    private VariavelService variavelService;
    @GetMapping
    public ResponseEntity<List<VariavelResponse>> findAll(){
        return new ResponseEntity<>(variavelService.
                 findAll()
                .stream()
                .map(dto -> new ModelMapper().map(dto, VariavelResponse.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<VariavelResponse>> findById(@PathVariable Integer id){
        return new ResponseEntity<>(Optional.of(
                new ModelMapper().map(variavelService.findById(id).get(), VariavelResponse.class)),
                HttpStatus.OK
        );
    }
    @PostMapping
    public ResponseEntity<VariavelResponse> add(@RequestBody VariavelRequest variavelRequest){
        VariavelDTO variavelDTO = variavelService.add(new ModelMapper().map(variavelRequest, VariavelDTO.class));
        return new ResponseEntity<>(new ModelMapper().map(variavelDTO, VariavelResponse.class), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<VariavelResponse> update(@RequestBody VariavelRequest variavelRequest, @PathVariable Integer id){
        VariavelDTO variavelDTO = variavelService.update(id, new ModelMapper().map(variavelRequest, VariavelDTO.class));
        return new ResponseEntity<>(new ModelMapper().map(variavelDTO, VariavelResponse.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        variavelService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
