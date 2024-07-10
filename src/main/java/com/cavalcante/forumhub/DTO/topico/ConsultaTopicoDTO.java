package com.cavalcante.forumhub.DTO.topico;

import com.cavalcante.forumhub.topico.Topico;

import java.time.LocalDateTime;

public record ConsultaTopicoDTO(Long id,
                                String titulo,
                                String mensagem,
                                LocalDateTime datacriacao,
                                String autor ) {

    public ConsultaTopicoDTO(Topico topico) {
            this(topico.getId(),
                    topico.getTitulo(),
                    topico.getMensagem(),
                    topico.getDatacriacao(),
                    topico.getAutor());
               }
}