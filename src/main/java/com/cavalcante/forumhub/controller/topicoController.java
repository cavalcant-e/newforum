package com.cavalcante.forumhub.controller;


import com.cavalcante.forumhub.DTO.topico.*;
import com.cavalcante.forumhub.repositories.TopicoRepository;
import com.cavalcante.forumhub.topico.Topico;
import com.cavalcante.forumhub.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topico")
public class topicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity novoTopico(@RequestBody @Valid DadosNovoTopicoDTO dados){
        Topico novoTopico = topicoService.criarTopico(dados);
        return ResponseEntity.ok(novoTopico);
    }

    @GetMapping
    public ResponseEntity <Page<ListaTopicoDTO>> listar(@PageableDefault(size = 10, sort = {"datacriacao"}, direction = Sort.Direction.ASC) Pageable paginacao){
        var page = topicoRepository.findAll(paginacao).map(ListaTopicoDTO::new);
        return ResponseEntity.ok(page);

    }

    @GetMapping ("/ativos")
    public ResponseEntity <Page<ListaTopicoDTO>> listarativos(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        var page = topicoRepository.findAllByStatusTrue(paginacao).map(ListaTopicoDTO::new);
        return ResponseEntity.ok(page);

    }
    @GetMapping("/respondidos")
    public ResponseEntity <Page<ListaTopicoDTO>> listarTopicosRespondidos(@PageableDefault(size = 10, sort = {"datacriacao"}, direction = Sort.Direction.ASC) Pageable paginacao){
        var page = topicoRepository.findByRespostaTrue(paginacao).map(ListaTopicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/semresposta")
    public ResponseEntity <Page<ListaTopicoDTO>> listarTopicosSemRespondidos(@PageableDefault(size = 10, sort = {"datacriacao"}, direction = Sort.Direction.ASC) Pageable paginacao){
        var page = topicoRepository.findByRespostaFalse(paginacao).map(ListaTopicoDTO::new);
        return ResponseEntity.ok(page);
    }


    @GetMapping ("/{id}")
    public ResponseEntity consultaTopico ( @PathVariable Long id) {
        ConsultaTopicoDTO consultaTopico = topicoService.consultaTopico(id);
        return ResponseEntity.ok(consultaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarTopico (@RequestBody @Valid AtualizarTopicoDTO dados){
        Topico topicoId = topicoRepository.getReferenceById(dados.id());
        topicoService.verificarDono(dados.usuarioid(),topicoId.getUsuarioid());
        topicoId.atualizarInformacoes(dados);
        topicoRepository.save(topicoId);
        return ResponseEntity.ok(new ConsultaTopicoDTO(topicoId));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity deletarTopico (@PathVariable Long id){
        topicoService.consultaTopico(id);
        topicoRepository.deleteById(id);
        return ResponseEntity.ok("topico excluido, nº " + id);
    }

    @PutMapping ("/fechar")
    @Transactional
    public ResponseEntity fecharTopico (@RequestBody FechartopicoDTO dados){
        Topico topicoId = topicoRepository.getReferenceById(dados.topicoid());
        topicoService.verificarDono(dados.usuarioid(),topicoId.getUsuarioid());
        topicoId.fechar();
        return ResponseEntity.ok("Topico nº"+ dados.topicoid() +" fechado.");
    }


}
