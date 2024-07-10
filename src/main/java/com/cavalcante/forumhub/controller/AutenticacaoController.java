package com.cavalcante.forumhub.controller;


import com.cavalcante.forumhub.DTO.DadosAutenticacaoDTO;
import com.cavalcante.forumhub.infra.security.DadosTokenJWTDTO;
import com.cavalcante.forumhub.infra.security.TokenService;
import com.cavalcante.forumhub.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/login")
public class AutenticacaoController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados){
        //o Spring recebe o DTO proprio do UsernamePasswordAuthenticationToken

        var AutenticacaoToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(AutenticacaoToken) ;

      var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

       var retorno = ResponseEntity.ok(new DadosTokenJWTDTO(tokenJWT));

      return retorno;



    }
}
