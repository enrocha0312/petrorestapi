package com.mindsim.petroapi.security.filters;

import com.mindsim.petroapi.entities.Usuario;
import com.mindsim.petroapi.repositories.UsuarioRepository;
import com.mindsim.petroapi.security.services.AuthenticationService;
import com.mindsim.petroapi.security.services.JWTTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JWTTokenService jwtTokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    //Metodo principal onde toda a requisicao tem de bater antes de chegar ao endpoint
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        Optional<Integer> id = jwtTokenService.obtainIdFromUsuario(token);
        if(!id.isPresent()){
            throw new InputMismatchException("Token invalido");
        }
        Usuario usuario = usuarioRepository.findById(id.get()).get();
        UserDetails userDetails = authenticationService.loadUserByUsername(usuario.getEmail());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    private String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        // verifica se veio algo sem ser espacos em branco dentro token
        if(!StringUtils.hasText(token)){
            return null; //ha espacos
        }
        return token.substring(7);//DEsconsiderando o BEarer
    }
}
