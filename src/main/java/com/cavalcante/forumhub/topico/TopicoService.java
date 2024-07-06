package com.cavalcante.forumhub.topico;


import com.cavalcante.forumhub.DTO.ConsultaTopicoDTO;
import com.cavalcante.forumhub.DTO.DadosNovaRespostaDTO;
import com.cavalcante.forumhub.DTO.DadosNovoTopicoDTO;
import com.cavalcante.forumhub.DTO.FechartopicoDTO;
import com.cavalcante.forumhub.exception.ValidacaoExcecao;
import com.cavalcante.forumhub.repositories.TopicoRepository;
import com.cavalcante.forumhub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Topico criarTopico(DadosNovoTopicoDTO dados){
      if (topicoRepository.existsByTituloAndMensagem(dados.titulo(),dados.mensagem())){
          throw new ValidacaoExcecao("Topico ja Cadastrado");
      }

      var  usuario = usuarioRepository.getReferenceById(dados.usuarioid());

      boolean status = true;
      var novoTopico = new Topico(null, dados.titulo(),dados.mensagem(),dados.datacriacao(),status, usuario.getNome(), false, dados.usuarioid());
      return topicoRepository.save(novoTopico);
  }


    public ConsultaTopicoDTO consultaTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacaoExcecao("Não existe Topico com esse ID");
        }
        return new ConsultaTopicoDTO(topicoRepository.getReferenceById(id));
    }

    public void verificarDono(Long usuarioid, Long topicoId) {

         if (usuarioid != topicoId){
          throw new ValidacaoExcecao("Alteração não permitida, usuario não é dono");
      }


    }





    public Topico verificarTopico(DadosNovaRespostaDTO dados) {

        Topico dadosTopico = topicoRepository.getReferenceById(dados.topicoid());
        if (!dadosTopico.isStatus()){
            throw new ValidacaoExcecao("Tópico fechado, não é possivel interagir. ");
        }

         dadosTopico.ativarResposta();

         return dadosTopico;
    }


//    public Topico atualizarTopico(Long id) {
//        if (!topicoRepository.existsById(id)) {
//            throw new ValidacaoExcecao("Não existe Topico com esse ID");
//        }
//      return null;
    }



