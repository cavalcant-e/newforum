package com.cavalcante.forumhub.infra.security;

import com.cavalcante.forumhub.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFiltro extends OncePerRequestFilter {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenAutorizadoJWT = recuperarToken(request);
        if (tokenAutorizadoJWT != null) {
            var usuarioLog = tokenService.pegarSubject(tokenAutorizadoJWT);

//            System.out.println(usuarioLog);
            var usuario = repository.findByEmail(usuarioLog);

            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        //A linha abaixo faz continuar o fluxo da requisão
        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {

        var autorizacaoHeader = request.getHeader("Authorization");

        if (autorizacaoHeader != null) {

            return autorizacaoHeader.replace("Bearer ", "");

        }
        return  null;

    }
}

