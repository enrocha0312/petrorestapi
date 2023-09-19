package com.mindsim.petroapi.repositories;

import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import com.mindsim.petroapi.entities.Medida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedidaRepository extends JpaRepository<Medida, MedidaId> {
}
