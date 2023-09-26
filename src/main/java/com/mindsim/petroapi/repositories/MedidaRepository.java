package com.mindsim.petroapi.repositories;

import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import com.mindsim.petroapi.entities.Medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface MedidaRepository extends JpaRepository<Medida, MedidaId> {
    @Query(value="select * from medida where tagid = ?1", nativeQuery = true)
    List<Medida> findByTagId(Integer tagId);
}
