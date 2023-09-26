package com.mindsim.petroapi.controllers;

import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import com.mindsim.petroapi.entities.Variavel;
import com.mindsim.petroapi.services.MedidaService;
import com.mindsim.petroapi.services.VariavelService;
import com.mindsim.petroapi.shared.MedidaRequest;
import com.mindsim.petroapi.shared.MedidaResponse;
import com.mindsim.petroapi.shared.VariavelResponse;
import com.mindsim.petroapi.shared.dto.MedidaDTO;
import com.mindsim.petroapi.shared.dto.VariavelDTO;
import jakarta.validation.Valid;
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
    @Autowired
    private VariavelService variavelService;
    @GetMapping
    public ResponseEntity<List<MedidaResponse>> findAll(){
        List<VariavelResponse> variavelResponseList = medidaService.findAll()
                .stream()
                .map(m -> m.getVariavelDTO())
                .map(m -> new ModelMapper().map(m, VariavelResponse.class))
                .collect(Collectors.toList());
        List<MedidaResponse> medidaResponseList = medidaService.findAll()
                .stream()
                .map(m -> new ModelMapper().map(m, MedidaResponse.class))
                .collect(Collectors.toList());
        for(MedidaResponse m : medidaResponseList){
            m.setVariavel(variavelResponseList.get(medidaResponseList.indexOf(m)));
        }
        return new ResponseEntity<>(medidaResponseList, HttpStatus.OK);
    }
    @GetMapping("/{id}/{timestamp}")
    public ResponseEntity<Optional<MedidaResponse>> findById(@PathVariable Integer id, @PathVariable LocalDateTime timestamp){
        VariavelDTO variavelDTO = variavelService.findById(id).get();
        MedidaId medidaId = new MedidaId(timestamp, new ModelMapper().map(variavelDTO, Variavel.class));
        return new ResponseEntity<>(Optional.of(
                new ModelMapper().map(medidaService.findById(medidaId).get(),MedidaResponse.class)), HttpStatus.OK);
    }
    @DeleteMapping("/{id}/{timestamp}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id, @PathVariable LocalDateTime timestamp){
        VariavelDTO variavelDTO = variavelService.findById(id).get();
        MedidaId medidaId = new MedidaId(timestamp, new ModelMapper().map(variavelDTO, Variavel.class));
        medidaService.deleteById(medidaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<Optional<MedidaResponse>> add(@Valid @RequestBody MedidaRequest medidaRequest){
        MedidaDTO medidaDTO = new ModelMapper().map(medidaRequest, MedidaDTO.class);
        medidaDTO.setVariavelDTO(new ModelMapper().map(medidaRequest.getVariavel(), VariavelDTO.class));
        medidaDTO = medidaService.add(medidaDTO);
        MedidaResponse medidaResponse = new ModelMapper().map(medidaDTO, MedidaResponse.class);
        medidaResponse.setVariavel(new ModelMapper().map(medidaDTO.getVariavelDTO(), VariavelResponse.class));
        return new ResponseEntity<>(Optional.of(medidaResponse), HttpStatus.CREATED);
    }
    @GetMapping("/{tagId}")
    public ResponseEntity<List<MedidaResponse>> findByTagId(@PathVariable Integer tagId){
        List<MedidaResponse> medidaResponseList = medidaService.findByTagId(tagId)
                .stream()
                .map(m->new ModelMapper().map(m, MedidaResponse.class))
                .collect(Collectors.toList());
        List<VariavelResponse> variavelResponseList = medidaService.findByTagId(tagId)
                .stream()
                .map(m->new ModelMapper().map(m.getVariavelDTO(), VariavelResponse.class))
                .collect(Collectors.toList());
        for(MedidaResponse m : medidaResponseList){
            m.setVariavel(variavelResponseList.get(medidaResponseList.indexOf(m)));
        }
        return new ResponseEntity<>(medidaResponseList, HttpStatus.OK);
    }
    @PutMapping("/{id}/{timestamp}")
    public ResponseEntity<MedidaResponse> update(@PathVariable Integer id, @PathVariable LocalDateTime timestamp, @RequestBody MedidaRequest medidaRequest){
        VariavelDTO variavelDTO = variavelService.findById(id).get();
        MedidaResponse medidaResponse = new ModelMapper().map(medidaService.update(new MedidaId(timestamp, new ModelMapper().map(variavelDTO, Variavel.class)), new ModelMapper().map(medidaRequest, MedidaDTO.class)), MedidaResponse.class);
        medidaResponse.setVariavel(new ModelMapper().map(variavelDTO, VariavelResponse.class));
        return new ResponseEntity<>(medidaResponse, HttpStatus.OK);
    }
}
