package com.cavalcante.forumhub.controller;


import com.cavalcante.forumhub.DTO.*;
import com.cavalcante.forumhub.repositories.RespostaRepository;
import com.cavalcante.forumhub.topico.Resposta;
import com.cavalcante.forumhub.topico.RespostaService;
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
@RequestMapping("/resposta")
public class repostaController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity responderTopico (@RequestBody @Valid DadosNovaRespostaDTO dados) {

       // topicoService.consultaTopico(dados.topicoid());

        Resposta novaResposta = respostaService.responder(dados);

       return ResponseEntity.ok(novaResposta);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarResposta (@RequestBody @Valid AtualizarRespostaDTO dados){

        ConsultaRespostaDTO atualizar = respostaService.atualizarResposta(dados);

        return ResponseEntity.ok(atualizar);
    }


    @GetMapping
    public ResponseEntity <Page<ListaRespostaDTO>> listar(@PageableDefault(size = 10, sort = {"datacriacao"}, direction = Sort.Direction.ASC) Pageable paginacao){
        var page = respostaRepository.findAll(paginacao).map(ListaRespostaDTO::new);

        return ResponseEntity.ok(page);

    }


    }


