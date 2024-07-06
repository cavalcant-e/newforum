package com.cavalcante.forumhub.DTO;

import jakarta.validation.constraints.NotNull;

public record AtualizarTopicoDTO(
                                @NotNull
                                Long id,
                                String titulo,
                                String mensagem,
                                Long usuarioid

) {
}
