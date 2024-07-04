package DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record novoTopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        LocalDateTime dataCriacao,

        boolean status,
        @NotBlank
        String autor,

        String respostas){
}
