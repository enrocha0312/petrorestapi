package com.mindsim.petroapi.repositories;

import com.mindsim.petroapi.entities.Influx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluxRepository extends JpaRepository<Influx, Integer> {
}
