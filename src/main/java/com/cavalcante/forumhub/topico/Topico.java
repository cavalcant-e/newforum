package com.cavalcante.forumhub.topico;


import com.cavalcante.forumhub.DTO.ConsultaTopicoDTO;
import com.cavalcante.forumhub.DTO.DadosNovoTopicoDTO;
import com.cavalcante.forumhub.DTO.atualizarTopicoDTO;
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

    private String resposta;


    public Topico(atualizarTopicoDTO dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();

    }

    public void atualizarInformacoes(atualizarTopicoDTO dados) {
        if(dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }


    }
}
