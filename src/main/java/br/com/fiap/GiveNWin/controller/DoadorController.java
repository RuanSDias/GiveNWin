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

import br.com.fiap.GiveNWin.Exception.RestNotFoundException;
import br.com.fiap.GiveNWin.models.Doador;
import br.com.fiap.GiveNWin.repository.DoadorRepository;
import jakarta.validation.Valid;

public class DoadorController {

    Logger log = LoggerFactory.getLogger(DoadorController.class);

    @Autowired
    DoadorRepository repository;

    @GetMapping("/api/usuarios")
    public List<Doador> index(){
        return repository.findAll();
    }

    @PostMapping("/api/usuarios")
    public ResponseEntity<Object> create(@RequestBody @Valid Doador doador){
        log.info("Cadastrando doador " + doador);
        repository.save(doador);
        return ResponseEntity.status(HttpStatus.CREATED).body(doador);
    }

    @GetMapping("/api/doacoes/{id}")
    public ResponseEntity<Doador> show(@PathVariable Long id){
        log.info("Buscando doador com id" + id);

        return ResponseEntity.ok().body(getDoador(id));
    }

    @DeleteMapping("/api/doacoes/{id}")
    public ResponseEntity<Doador> destroy(@PathVariable Long id){
        log.info("Excluindo doador com id" + id);

        repository.delete(getDoador(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/doacoes/{id}")
    public ResponseEntity<Doador> update(@PathVariable Long id, @RequestBody Doador doador){
        log.info("Atualizando doador com id" + id);
        getDoador(id);

        doador.setId(id);
        repository.save(doador);
        return ResponseEntity.status(HttpStatus.OK).body(doador);
    }

    private Doador getDoador(Long id) {
        return repository.findById(id)
             .orElseThrow(() -> new RestNotFoundException("Doador não encontrado"));
    }
    
}
