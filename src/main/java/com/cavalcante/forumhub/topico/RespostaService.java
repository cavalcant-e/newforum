package com.cavalcante.forumhub.topico;

import com.cavalcante.forumhub.DTO.resposta.ConsultaRespostaDTO;
import com.cavalcante.forumhub.DTO.resposta.ConsultaRespostaDetalheDTO;
import com.cavalcante.forumhub.DTO.resposta.DadosNovaRespostaDTO;
import com.cavalcante.forumhub.DTO.topico.AtualizarRespostaDTO;
import com.cavalcante.forumhub.infra.exception.ValidacaoExcecao;
import com.cavalcante.forumhub.repositories.RespostaRepository;
import com.cavalcante.forumhub.repositories.TopicoRepository;
import com.cavalcante.forumhub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    ConsultaRespostaDetalheDTO consultaRespostaDetalheDTO;


    public Resposta responder(DadosNovaRespostaDTO dados) {
         Topico dadosTopico =  topicoService.verificarTopico(dados);
         var usuario = usuarioRepository.getReferenceById(dados.usuarioid());

        LocalDateTime data = LocalDateTime.now();
         var resposta =  new Resposta(null, dadosTopico.getTitulo(), dadosTopico.getMensagem(),
                 dadosTopico.getId(), dados.usuarioid(), usuario.getNome(),dados.solucao(), data);

        return respostaRepository.save(resposta);
    }

    public ConsultaRespostaDTO atualizarResposta(AtualizarRespostaDTO dados) {
        topicoService.consultaTopico(dados.topicoid());
        Resposta consultaRespostaID = respostaRepository.getReferenceById(dados.respostaid());
        topicoService.verificarDono(consultaRespostaID.getUsuarioid(),dados.usuarioid());
        consultaRespostaID.atualizarInformacoes(dados);
        return new ConsultaRespostaDTO(consultaRespostaID.getTopicoId(),consultaRespostaID.getMensagem(), consultaRespostaID.getSolucao());
    }

    private void verificardono(Long usuarioIdBd, Long usuarioIdJson) {
        if (usuarioIdBd != usuarioIdJson){
            throw new ValidacaoExcecao("Alteração não permitida, usuario não é dono");

        }

    }


    private List<ConsultaRespostaDetalheDTO> converteDados(List<Resposta> respostas){
        return  respostas.stream()
                .map(r -> new ConsultaRespostaDetalheDTO(r.getTopicoId(),r.getTitulo(),r.getMensagem(),r.getSolucao(),r.getAutor(),r.getDatacriacao()))
                .collect(Collectors.toList());
        }

    public List<ConsultaRespostaDetalheDTO> consultaResposta( Long id) {
        if (!respostaRepository.existsById(id)) {
            throw new ValidacaoExcecao("Não existe resposta para este IDx.");
        }
                return converteDados(respostaRepository.findByTopicoId(id));

    }


}
// Iterar sobre a lista de respostas e acessar os dados de cada resposta
//        for (Resposta resposta : respostas) {
//            // Acessar os dados da resposta, como:
//            Long topicoid = resposta.getTopicoid();
//            String titulo = resposta.getTitulo();
//            String mensagem = resposta.getMensagem();
//            String solucao = resposta.getSolucao();
//            String autor = resposta.getAutor();
//            LocalDateTime datacriaca = resposta.getDatacriacao();
//
//            Resposta resposta1 = new Resposta();
//
//            resposta1.setTopicoid(topicoid);
//            resposta1.setTitulo(titulo);
//            resposta1.setMensagem(mensagem);
//            resposta1.setSolucao(solucao);
//            resposta1.setAutor(autor);
//            resposta1.setDatacriacao(datacriaca);
//
//        }