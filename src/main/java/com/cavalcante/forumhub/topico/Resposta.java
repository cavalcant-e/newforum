package com.cavalcante.forumhub.topico;

import com.cavalcante.forumhub.DTO.AtualizarRespostaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private Long topicoid;

    private Long usuarioid;

    private String autor;

    private String solucao;

    private LocalDateTime datacriacao;

    public void atualizarInformacoes(AtualizarRespostaDTO dados) {
        if(dados.solucao() != null) {
            this.solucao = dados.solucao();
        }

    }
}