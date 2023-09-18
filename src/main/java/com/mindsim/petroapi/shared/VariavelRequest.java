package com.mindsim.petroapi.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariavelRequest {
    private String tag;
    private String name;
    private String mfgName;
    private String plataforma;
}
