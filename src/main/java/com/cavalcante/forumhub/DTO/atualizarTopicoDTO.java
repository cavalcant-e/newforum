package com.cavalcante.forumhub.DTO;

import jakarta.validation.constraints.NotNull;

public record atualizarTopicoDTO(
                                @NotNull
                                Long id,
                                String titulo,
                                String mensagem

) {
}
