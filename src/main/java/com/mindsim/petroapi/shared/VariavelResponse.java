package com.mindsim.petroapi.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariavelResponse {
    private Integer id;
    private String tag;
    private String name;
    private String mfgName;
    private String plataforma;
}
