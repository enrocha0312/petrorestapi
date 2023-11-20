package com.mindsim.petroapi.controllers;

import com.mindsim.petroapi.entities.Usuario;
import com.mindsim.petroapi.services.UsuarioService;
import com.mindsim.petroapi.shared.LoginRequest;
import com.mindsim.petroapi.shared.LoginResponse;
import com.mindsim.petroapi.shared.dto.LoginDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/petroapi/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario){
        Usuario user = usuarioService.adicionar(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request){
        LoginDTO loginDTO = usuarioService.doLogin(request.getEmail(), request.getSenha());
        LoginResponse loginResponse = new ModelMapper().map(loginDTO, LoginResponse.class);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
