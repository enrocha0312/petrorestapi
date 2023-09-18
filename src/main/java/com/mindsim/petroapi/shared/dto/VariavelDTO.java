package com.mindsim.petroapi.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariavelDTO {
    private Integer id;
    private String tag;
    private String plataforma;
    private String name;
    private String mfgName;
}
