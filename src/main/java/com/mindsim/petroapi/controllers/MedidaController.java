package com.mindsim.petroapi.controllers;

import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import com.mindsim.petroapi.services.MedidaService;
import com.mindsim.petroapi.shared.MedidaRequest;
import com.mindsim.petroapi.shared.MedidaResponse;
import com.mindsim.petroapi.shared.dto.MedidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/petroapi/medidas")
public class MedidaController {
    @Autowired
    private MedidaService medidaService;
    @GetMapping
    public ResponseEntity<List<MedidaResponse>> findAll(){
        return new ResponseEntity<>(medidaService.findAll()
                .stream()
                .map(m -> new ModelMapper().map(m, MedidaResponse.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping("/{id}/{timestamp}")
    public ResponseEntity<Optional<MedidaResponse>> findById(@PathVariable Integer id, @PathVariable LocalDateTime timestamp){
        MedidaId medidaId = new MedidaId(timestamp, id);
        return new ResponseEntity<>(Optional.of(
                new ModelMapper().map(medidaService.findById(medidaId).get(),MedidaResponse.class)), HttpStatus.OK);
    }
    @DeleteMapping("/{id}/{timestamp}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id, @PathVariable LocalDateTime timestamp){
        MedidaId medidaId = new MedidaId(timestamp, id);
        medidaService.deleteById(medidaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<Optional<MedidaResponse>> add(@RequestBody MedidaRequest medidaRequest){
        MedidaDTO medidaDTO = medidaService.add(new ModelMapper().map(medidaRequest, MedidaDTO.class));
        return new ResponseEntity<>(Optional.of(new ModelMapper().map(medidaDTO, MedidaResponse.class)), HttpStatus.CREATED);
    }
    @GetMapping("/{tagId}")
    public ResponseEntity<Set<MedidaResponse>> findByTagId(@PathVariable Integer tagId){
        return new ResponseEntity<>(medidaService.findByTagId(tagId)
                .stream()
                .map(m->new ModelMapper().map(m, MedidaResponse.class))
                .collect(Collectors.toSet()), HttpStatus.OK);
    }
    @PutMapping("/{id}/{timestamp}")
    public ResponseEntity<MedidaResponse> update(@PathVariable Integer id, @PathVariable LocalDateTime timestamp, @RequestBody MedidaRequest medidaRequest){
        MedidaResponse medidaResponse = new ModelMapper().map(medidaService.update(new MedidaId(timestamp, id), new ModelMapper().map(medidaRequest, MedidaDTO.class)), MedidaResponse.class);
        return new ResponseEntity<>(medidaResponse, HttpStatus.OK);
    }
}
