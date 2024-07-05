package com.cavalcante.forumhub.DTO;


import com.cavalcante.forumhub.topico.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosNovoTopicoDTO(


        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        LocalDateTime datacriacao,

        @NotBlank
        String autor,

        String resposta){

        }

