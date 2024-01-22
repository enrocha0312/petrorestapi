package com.mindsim.petroapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MensagemEmail {
    private String assunto;
    private String texto;
    private String remetente;
    private List<String> destinatarios;
}
