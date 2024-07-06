package com.cavalcante.forumhub.topico;

import com.cavalcante.forumhub.DTO.AtualizarRespostaDTO;
import com.cavalcante.forumhub.DTO.ConsultaRespostaDTO;
import com.cavalcante.forumhub.DTO.DadosNovaRespostaDTO;
import com.cavalcante.forumhub.exception.ValidacaoExcecao;
import com.cavalcante.forumhub.repositories.RespostaRepository;
import com.cavalcante.forumhub.repositories.TopicoRepository;
import com.cavalcante.forumhub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespostaService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Resposta responder(DadosNovaRespostaDTO dados) {
         Topico dadosTopico =  topicoService.verificarTopico(dados);
         var usuario = usuarioRepository.getReferenceById(dados.usuarioid());
         var resposta =  new Resposta(null, dadosTopico.getTitulo(), dadosTopico.getMensagem(),
                 dadosTopico.getId(), dados.usuarioid(), usuario.getNome(),dados.solucao(), dados.datacriacao());

        return respostaRepository.save(resposta);
    }

    public ConsultaRespostaDTO atualizarResposta(AtualizarRespostaDTO dados) {
        topicoService.consultaTopico(dados.topicoid());
        Resposta consultaRespostaID = respostaRepository.getReferenceById(dados.respostaid());
        topicoService.verificarDono(consultaRespostaID.getUsuarioid(),dados.usuarioid());
        consultaRespostaID.atualizarInformacoes(dados);

        return new ConsultaRespostaDTO(consultaRespostaID.getTopicoid(),consultaRespostaID.getMensagem(), consultaRespostaID.getSolucao());
    }

    private void verificardono(Long usuarioIdBd, Long usuarioIdJson) {
        if (usuarioIdBd != usuarioIdJson){
            throw new ValidacaoExcecao("Alteração não permitida, usuario não é dono");

        }

    }


}
