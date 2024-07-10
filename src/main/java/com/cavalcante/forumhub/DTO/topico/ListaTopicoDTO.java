package com.cavalcante.forumhub.DTO.topico;

import com.cavalcante.forumhub.topico.Topico;

import java.time.LocalDateTime;

public record ListaTopicoDTO(
                            Long id,
                            String titulo,
                            String mensagem,
                            LocalDateTime datacriacao,
                            Boolean status,
                            String autor,
                            Boolean resposta) {
    public ListaTopicoDTO (Topico topico) {
                this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDatacriacao(),
                     topico.isStatus(),topico.getAutor(), topico.isResposta());
    }
}
