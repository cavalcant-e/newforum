package com.cavalcante.forumhub.DTO.topico;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosNovoTopicoDTO(


        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,

        LocalDateTime datacriacao,
        @NotNull
        Long usuarioid,

        String autor,

        boolean resposta){

        }

