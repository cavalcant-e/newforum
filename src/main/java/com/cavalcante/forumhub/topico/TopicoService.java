package com.cavalcante.forumhub.topico;


import com.cavalcante.forumhub.DTO.topico.ConsultaTopicoDTO;
import com.cavalcante.forumhub.DTO.resposta.DadosNovaRespostaDTO;
import com.cavalcante.forumhub.DTO.topico.DadosNovoTopicoDTO;
import com.cavalcante.forumhub.infra.exception.ValidacaoExcecao;
import com.cavalcante.forumhub.repositories.TopicoRepository;
import com.cavalcante.forumhub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Topico criarTopico(DadosNovoTopicoDTO dados) {
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new ValidacaoExcecao("Topico ja Cadastrado");
        }
        var usuario = usuarioRepository.getReferenceById(dados.usuarioid());
        boolean status = true;
        LocalDateTime data = LocalDateTime.now();
        var novoTopico = new Topico(null, dados.titulo(), dados.mensagem(), data, status, usuario.getNome(), false, dados.usuarioid());
        return topicoRepository.save(novoTopico);
    }


    public ConsultaTopicoDTO consultaTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacaoExcecao("Não existe Topico com esse ID");
        }
        return new ConsultaTopicoDTO(topicoRepository.getReferenceById(id));
    }

    public void verificarDono(Long usuarioid, Long topicoId) {
        if (usuarioid != topicoId) {
            throw new ValidacaoExcecao("Alteração não permitida, usuario não é dono");
        }
    }

    public Topico verificarTopico(DadosNovaRespostaDTO dados) {
        Topico dadosTopico = topicoRepository.getReferenceById(dados.topicoid());
        if (!dadosTopico.isStatus()) {
            throw new ValidacaoExcecao("Tópico fechado, não é possivel interagir. ");
        }
        dadosTopico.ativarResposta();
        return dadosTopico;
    }
}



