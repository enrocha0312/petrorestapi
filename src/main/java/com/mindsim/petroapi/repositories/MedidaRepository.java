package com.mindsim.petroapi.repositories;

import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import com.mindsim.petroapi.entities.Medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Set;

public interface MedidaRepository extends JpaRepository<Medida, MedidaId> {
    @Query(value="select * from medida where tagid = ?1", nativeQuery = true)
    Set<Medida> findByTagId(Integer tagId);
}
