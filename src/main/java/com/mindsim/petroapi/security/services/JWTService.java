package com.mindsim.petroapi.security.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JWTService {
    @Value("${jwt.secret}")
    private static String SECRET;

}
