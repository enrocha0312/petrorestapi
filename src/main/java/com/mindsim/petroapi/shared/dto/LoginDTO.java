package com.mindsim.petroapi.shared.dto;

import com.mindsim.petroapi.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    private String token;
    private Usuario usuario;
}
