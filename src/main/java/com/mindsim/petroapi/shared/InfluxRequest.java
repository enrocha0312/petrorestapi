package com.mindsim.petroapi.shared;

import java.time.LocalDateTime;

public class InfluxRequest {
    private LocalDateTime time;
    private Double value;
    private String field;
    private String measurement;
    private String mfg;
    private String name;
    private String plataforma;
}