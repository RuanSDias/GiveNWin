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
import br.com.fiap.GiveNWin.models.Receptor;
import br.com.fiap.GiveNWin.repository.ReceptorRepository;
import jakarta.validation.Valid;

@RestController
public class ReceptorController {
    Logger log = LoggerFactory.getLogger(DoadorController.class);

    @Autowired
    ReceptorRepository repository;

    @GetMapping("/api/receptores")
    public List<Receptor> index(){
        return repository.findAll();
    }

    @PostMapping("/api/receptores")
    public ResponseEntity<Object> create(@RequestBody @Valid Receptor receptor){
        log.info("Cadastrando receptor " + receptor);
        repository.save(receptor);
        return ResponseEntity.status(HttpStatus.CREATED).body(receptor);
    }

    @GetMapping("/api/receptores/{id}")
    public ResponseEntity<Receptor> show(@PathVariable Long id){
        log.info("Buscando receptor com id" + id);

        return ResponseEntity.ok().body(getReceptor(id));
    }

    @DeleteMapping("/api/receptores/{id}")
    public ResponseEntity<Receptor> destroy(@PathVariable Long id){
        log.info("Excluindo receptor com id" + id);

        repository.delete(getReceptor(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/receptores/{id}")
    public ResponseEntity<Receptor> update(@PathVariable Long id, @RequestBody Receptor receptor){
        log.info("Atualizando receptor com id" + id);
        getReceptor(id);

        receptor.setId(id);
        repository.save(receptor);
        return ResponseEntity.status(HttpStatus.OK).body(receptor);
    }
 
    private Receptor getReceptor(Long id) {
        return repository.findById(id)
             .orElseThrow(() -> new RestNotFoundException("Receptor não encontrado"));
    }
}
