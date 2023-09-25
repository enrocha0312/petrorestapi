package com.mindsim.petroapi.shared;


import com.mindsim.petroapi.entities.Variavel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedidaRequest implements Serializable {
    private Double valor;
    private Variavel variavel;
}
