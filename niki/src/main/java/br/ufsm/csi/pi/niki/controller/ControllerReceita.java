package br.ufsm.csi.pi.niki.controller;

import br.ufsm.csi.pi.niki.model.ContaUsuario;
import br.ufsm.csi.pi.niki.model.Receita;
import br.ufsm.csi.pi.niki.repository.RepositorioContaUsuario;
import br.ufsm.csi.pi.niki.repository.RepositorioReceita;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController

public class ControllerReceita {

    final
    RepositorioReceita repositorioReceita;

    public ControllerReceita(RepositorioReceita repositorioReceita) {
        this.repositorioReceita = repositorioReceita;
    }

    @GetMapping("/receita")
    public ResponseEntity<List<Receita>> getAllReceitas(){
        try{
            List<Receita> receitas = new ArrayList<>(repositorioReceita.findAll());

            if(receitas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(receitas, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/receita/{receita_id}")
    public ResponseEntity<Receita> getReceitaById (@PathVariable("receita_id") int receita_id){
        Optional<Receita> receitaData = repositorioReceita.findById(receita_id);

        return receitaData.map(receita -> new ResponseEntity<>(receita, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/receita")
    public ResponseEntity<Receita> addReceita(@RequestBody Receita receita) {
        Receita _receita = repositorioReceita.save(new Receita(
                receita.getReceita_nome(),
                receita.getReceita_categoria(),
                receita.getReceita_ingredientes(),
                receita.getReceita_mododepreparo(),
                receita.getReceita_porcoes(),
                receita.getReceita_tempodepreparo())
        );
        return new ResponseEntity<>(_receita, HttpStatus.CREATED);
    }

    @PutMapping("/receita/{receita_id}")
    public ResponseEntity<Receita> editReceita(@PathVariable("receita_id") int receita_id,
                                                    @RequestBody Receita receita) {
        Optional<Receita> receitaData = repositorioReceita.findById(receita_id);

        if (receitaData.isPresent()) {
            Receita _receita = receitaData.get();
            _receita.setReceita_nome(receita.getReceita_nome());
            _receita.setReceita_categoria(receita.getReceita_categoria());
            _receita.setReceita_ingredientes(receita.getReceita_ingredientes());
            _receita.setReceita_mododepreparo(receita.getReceita_mododepreparo());
            _receita.setReceita_porcoes(receita.getReceita_porcoes());
            _receita.setReceita_tempodepreparo(receita.getReceita_tempodepreparo());

            return new ResponseEntity<>(repositorioReceita.save(_receita), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/receita/{receita_id}")
    public ResponseEntity<Receita> deleteReceitaById(@PathVariable("receita_id") int receita_id) {

        try {
            repositorioReceita.deleteById(receita_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
