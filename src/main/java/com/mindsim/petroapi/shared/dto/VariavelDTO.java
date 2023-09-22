package com.mindsim.petroapi.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariavelDTO implements Serializable {
    private Integer id;
    private String tag;
    private String plataforma;
    private String name;
    private String mfgName;
}
