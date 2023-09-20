package com.mindsim.petroapi.shared.wrappers;

import com.mindsim.petroapi.entities.Variavel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariavelWrapper {
    private List<Variavel> variaveis = new ArrayList<>();
}
