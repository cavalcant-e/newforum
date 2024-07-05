package com.cavalcante.forumhub.controller;


import com.cavalcante.forumhub.DTO.ConsultaTopicoDTO;
import com.cavalcante.forumhub.DTO.DadosNovoTopicoDTO;
import com.cavalcante.forumhub.DTO.atualizarTopicoDTO;
import com.cavalcante.forumhub.repositories.TopicoRepository;
import com.cavalcante.forumhub.topico.Topico;
import com.cavalcante.forumhub.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/topico")

public class topicoController {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity novoTopico(@RequestBody @Valid DadosNovoTopicoDTO dados){

       Topico novoTopico = topicoService.criarTopico(dados);

        return ResponseEntity.ok(novoTopico);
    }

    @GetMapping ("/{id}")
    public ResponseEntity consultaTopico ( @PathVariable Long id) {


        ConsultaTopicoDTO consultaTopico = topicoService.ConultaTopico(id);

        return ResponseEntity.ok(consultaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarTopico (@RequestBody @Valid atualizarTopicoDTO dados){
        Topico topicoId = topicoRepository.getReferenceById(dados.id());

        topicoId.atualizarInformacoes(dados);

        topicoRepository.save(topicoId);

        return ResponseEntity.ok(new ConsultaTopicoDTO(topicoId));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity deletarTopico (@PathVariable Long id){

        topicoRepository.deleteById(id);

        return ResponseEntity.ok("topico excluido, nº " + id);

    }

}
