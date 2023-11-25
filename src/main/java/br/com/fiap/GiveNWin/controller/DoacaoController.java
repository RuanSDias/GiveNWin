package br.com.fiap.GiveNWin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.GiveNWin.Exception.RestNotFoundException;
import br.com.fiap.GiveNWin.models.Doacao;
import br.com.fiap.GiveNWin.repository.DoacaoRepository;
import jakarta.validation.Valid;

@RestController
public class DoacaoController {

    Logger log = LoggerFactory.getLogger(DoacaoController.class);

    @Autowired
    DoacaoRepository repository;

    @GetMapping("/api/doacoes")
    public List<Doacao> index(){
        return repository.findAll();
    }

    @PostMapping("/api/doacoes")
    public ResponseEntity<Object> create(@RequestBody @Valid Doacao doacao){
        log.info("Cadastrando doação " + doacao);
        repository.save(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(doacao);
    }

    @GetMapping("/api/doacoes/{id}")
    public ResponseEntity<Doacao> show(@PathVariable Long id){
        log.info("Buscando doação com id" + id);

        return ResponseEntity.ok().body(getDoacao(id));
    }

    @DeleteMapping("/api/doacoes/{id}")
    public ResponseEntity<Doacao> destroy(@PathVariable Long id){
        log.info("Excluindo doacao com id" + id);

        repository.delete(getDoacao(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/doacoes/{id}")
    public ResponseEntity<Doacao> update(@PathVariable Long id, @RequestBody Doacao doacao){
        log.info("Atualizando cupom com id" + id);
        getDoacao(id);

        doacao.setId(id);
        repository.save(doacao);
        return ResponseEntity.status(HttpStatus.OK).body(doacao);
    }

    private Doacao getDoacao(Long id) {
        return repository.findById(id)
             .orElseThrow(() -> new RestNotFoundException("Doação não encontrada"));
    }
    
}
