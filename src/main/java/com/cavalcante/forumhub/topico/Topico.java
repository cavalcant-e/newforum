package com.cavalcante.forumhub.topico;


import com.cavalcante.forumhub.DTO.AtualizarTopicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;



@Entity
@Table (name = "topicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime datacriacao;

    private boolean status;

    private String autor;

    private boolean resposta;

    private Long usuarioid;

    public Topico(AtualizarTopicoDTO dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();

    }

    public void atualizarInformacoes(AtualizarTopicoDTO dados) {
        if(dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }


    }

    public void ativarResposta() {
        this.resposta = true;
    }

    public void fechar() {
        this.status = false;

    }
}
