package com.cavalcante.forumhub.DTO;

import com.cavalcante.forumhub.topico.Resposta;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

public record ListaRespostaDTO(Long topicoid,
                               String titulo,
                               String Mensagem,
                               Long respostaid,
                               String autor,
                               String resposta,
                               LocalDateTime datacriacao) {
    public ListaRespostaDTO (Resposta resposta){
            this(resposta.getTopicoid(),resposta.getTitulo(),resposta.getMensagem(), resposta.getId(),
                    resposta.getAutor(), resposta.getSolucao(), resposta.getDatacriacao());

    }
}
