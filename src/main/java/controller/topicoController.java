package controller;


import DTO.novoTopicoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import repositories.topicoRepository;
import topico.Topico;

@RestController
@RequestMapping("/topico")

public class topicoController {
    @Autowired
    private topicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity novotopico (@RequestBody @Valid novoTopicoDTO dados){
        return ResponseEntity.ok(null);}

    @GetMapping
    public String  teste() {
       return "teste";
    }

}
