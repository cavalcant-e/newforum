package controller;


import DTO.novoTopicoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import repositories.topicoRepository;
import topico.Topico;

import java.util.List;

@RestController
//@RequestMapping("/topicos")
public class topicoController {

    @Autowired
    private topicoRepository topicoRepository;

    @Autowired
    private Topico topico;

    @PostMapping
    @Transactional
    public ResponseEntity novotopico (@RequestBody @Valid novoTopicoDTO dados){

        return ResponseEntity.ok(null);

    }

    @GetMapping ("/teste")
    public String  teste() {
       return "teste";
    }

}
