package com.cavalcante.forumhub.DTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosNovaRespostaDTO(
                                @NotNull
                                Long topicoid,
                                @NotNull
                                Long usuarioid,
                                String autor,
                                String solucao,
                                LocalDateTime datacriacao


) {
}
