package com.mindsim.petroapi.controllers;

import com.mindsim.petroapi.services.InfluxService;
import com.mindsim.petroapi.shared.InfluxRequest;
import com.mindsim.petroapi.shared.InfluxResponse;
import com.mindsim.petroapi.shared.dto.InfluxDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/petroapi/influx")
public class InfluxController {
    @Autowired
    private InfluxService influxService;

    @PostMapping
    public ResponseEntity<InfluxResponse> add(@RequestBody InfluxRequest influxRequest){
        InfluxDTO influxDTO = influxService.add(new ModelMapper().map(influxRequest, InfluxDTO.class));
        return new ResponseEntity<>(new ModelMapper().map(influxDTO, InfluxResponse.class), HttpStatus.CREATED);
    }
    @PostMapping("/influxlisttocsv")
    public ResponseEntity<Map<String, List<InfluxResponse>>> sendAListOfInfluxToCSV(@RequestBody Map<String, List<InfluxRequest>> influxMap){
        AtomicReference<List<InfluxDTO>> listAtomicReference = new AtomicReference<>();
        influxMap.values().forEach(v->{
            listAtomicReference.set(v.stream().map(i -> influxService.add(new ModelMapper().map(i, InfluxDTO.class))).collect(Collectors.toList()));
        });
        Map<String, List<InfluxResponse>> mapOfResponses = new HashMap<>();
        influxMap.keySet().forEach(k->mapOfResponses.put(k, new ArrayList<>()));
        for(String k : mapOfResponses.keySet()){
            mapOfResponses.get(k).addAll(listAtomicReference
                    .get()
                    .stream()
                    .map(i -> new ModelMapper().map(i, InfluxResponse.class))
                    .collect(Collectors.toList()));
        }
        return new ResponseEntity<>(mapOfResponses, HttpStatus.CREATED);
    }
}
