package com.mindsim.petroapi.services;

import com.mindsim.petroapi.entities.Influx;
import com.mindsim.petroapi.repositories.InfluxRepository;
import com.mindsim.petroapi.shared.dto.InfluxDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class InfluxService {
    @Autowired
    private InfluxRepository influxRepository;

    public InfluxDTO add (InfluxDTO influxDTO){
        influxDTO.setId(null);
        Influx influx = new ModelMapper().map(influxDTO, Influx.class);
        influx = influxRepository.save(influx);
        influxDTO.setId(influx.getId());
        return influxDTO;
    }

    public Map<String, List<InfluxDTO>> addManyInfluxObjects(Map<String, List<InfluxDTO>> influxMap){
        AtomicReference<Influx> influxAtomicReference = new AtomicReference<>();
        influxMap.values().forEach(l->{
            for(InfluxDTO i: l){
                i.setId(null);
                influxAtomicReference.set(influxRepository.save(new ModelMapper().map(i, Influx.class)));
                i.setId(influxAtomicReference.get().getId());
            }
        });
        return influxMap;
    }
}
