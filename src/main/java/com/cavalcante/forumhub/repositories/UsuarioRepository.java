package com.cavalcante.forumhub.repositories;

import com.cavalcante.forumhub.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
}
