package com.cavalcante.forumhub.repositories;

import com.cavalcante.forumhub.topico.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
