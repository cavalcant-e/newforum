package com.cavalcante.forumhub.DTO.resposta;



import java.time.LocalDateTime;

public record ConsultaRespostaDetalheDTO(Long topicoid,
                                         String titulo,
                                         String mensagem,
                                         String solucao,
                                         String autor,
                                         LocalDateTime datacriacao) {
}

