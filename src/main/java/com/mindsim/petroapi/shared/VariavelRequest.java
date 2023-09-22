package com.mindsim.petroapi.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariavelRequest implements Serializable {
    private String tag;
    private String name;
    private String mfgName;
    private String plataforma;
}
