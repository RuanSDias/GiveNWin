package br.com.fiap.GiveNWin.controller;

import java.util.List;
import java.util.Random;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.GiveNWin.Exception.RestNotFoundException;
import br.com.fiap.GiveNWin.models.Cupom;
import br.com.fiap.GiveNWin.repository.CupomRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cupons")
public class CupomController {

    Logger log = LoggerFactory.getLogger(CupomController.class);

    @Autowired
    CupomRepository repository;

    @GetMapping
    public List<Cupom> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Cupom cupom){
        log.info("Cadastrando cupom " + cupom);
        int randomNumber = new Random().nextInt(5000);
        String randCod = String.valueOf(randomNumber);
        cupom.setCodigo(randCod);
        repository.save(cupom);
        return ResponseEntity.status(HttpStatus.CREATED).body(cupom);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cupom> show(@PathVariable Long id){
        log.info("Buscando cupom com id" + id);

        return ResponseEntity.ok(getCupom(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cupom> destroy(@PathVariable Long id){
        log.info("Excluindo cupom com id" + id);

        repository.delete(getCupom(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}/{codigo}")
    public ResponseEntity<Cupom> update(@PathVariable Long id, @PathVariable String codigo, @RequestBody Cupom cupom){
        log.info("Atualizando cupom com id" + id);
        getCupom(id);

        cupom.setId(id);
        cupom.setCodigo(codigo);
        repository.save(cupom);
        return ResponseEntity.status(HttpStatus.OK).body(cupom);
    }

    private Cupom getCupom(Long id) {
        return repository.findById(id)
             .orElseThrow(() -> new RestNotFoundException("Cupom não encontrado"));
    }
}