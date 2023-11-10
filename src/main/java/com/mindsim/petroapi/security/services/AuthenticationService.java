package com.mindsim.petroapi.security.services;

import com.mindsim.petroapi.entities.Usuario;
import com.mindsim.petroapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UsuarioByFunction(()->usuarioRepository.findByEmail(email));
    }
    private UserDetails UsuarioByFunction(Supplier<Optional<Usuario>> supplier) {
        return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
    }
}
