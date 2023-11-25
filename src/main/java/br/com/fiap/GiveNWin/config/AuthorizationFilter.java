package br.com.fiap.GiveNWin.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.GiveNWin.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationFilter {
    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        var token = getToken(request);
        System.out.println(token);

        if (token != null){
            var usuario = tokenService.getValidateUser(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header == null || header.isEmpty() || header.startsWith("Bearer ")){
            return null;
        }
        return header.replace("Bearer ", "");
    }
    
}
