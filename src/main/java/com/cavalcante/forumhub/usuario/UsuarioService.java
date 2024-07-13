package com.cavalcante.forumhub.usuario;

import com.cavalcante.forumhub.DTO.CadastroUsuarioDTO;
import com.cavalcante.forumhub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario cadastro(CadastroUsuarioDTO dados) {


      String password = new BCryptPasswordEncoder().encode(dados.senha());

      Usuario novoUsuario = new Usuario(null, dados.nome(), dados.email(), password);

      return usuarioRepository.save(novoUsuario);

    }

    public Long consultaUsuario(String email) {

        Usuario usuarioid = (Usuario) usuarioRepository.findByEmail(email);

        return usuarioid.getId();

    }
}
