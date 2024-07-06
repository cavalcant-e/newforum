package com.cavalcante.forumhub.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cavalcante.forumhub.topico.Topico;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface TopicoRepository extends JpaRepository<Topico, Long> {


    Page<Topico> findAllByStatusTrue(Pageable paginacao);

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findByRespostaTrue(Pageable paginacao);

    Page<Topico> findByRespostaFalse(Pageable paginacao);
}