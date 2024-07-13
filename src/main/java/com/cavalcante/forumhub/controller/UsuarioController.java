package com.cavalcante.forumhub.controller;


import com.cavalcante.forumhub.DTO.CadastroUsuarioDTO;
import com.cavalcante.forumhub.usuario.Usuario;
import com.cavalcante.forumhub.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastroUsuario (@RequestBody @Valid CadastroUsuarioDTO dados){

       Usuario cadastro = usuarioService.cadastro(dados);

       Long usuarioid = usuarioService.consultaUsuario(dados.email());

        return ResponseEntity.ok("Cadastro realiazado com sucesso, seu ID para interação é: "+ usuarioid);

    }
}
