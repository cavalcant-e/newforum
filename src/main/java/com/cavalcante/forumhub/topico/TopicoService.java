package com.cavalcante.forumhub.topico;


import com.cavalcante.forumhub.DTO.ConsultaTopicoDTO;
import com.cavalcante.forumhub.DTO.DadosNovoTopicoDTO;
import com.cavalcante.forumhub.exception.ValidacaoExcecao;
import com.cavalcante.forumhub.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;


  public Topico criarTopico(DadosNovoTopicoDTO dados){

      boolean status =true;
      var novoTopico = new Topico(null, dados.titulo(),dados.mensagem(),dados.datacriacao(),status, dados.autor(), null);

      return topicoRepository.save(novoTopico);
  }


    public ConsultaTopicoDTO ConultaTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacaoExcecao("Não existe Topico com esse ID");
        }

      return new ConsultaTopicoDTO(topicoRepository.getReferenceById(id));
    }

    public Topico atualizarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacaoExcecao("Não existe Topico com esse ID");
        }


      return null;
    }
}
