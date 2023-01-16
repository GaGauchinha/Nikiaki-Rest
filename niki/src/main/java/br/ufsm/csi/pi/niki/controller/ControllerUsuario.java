package br.ufsm.csi.pi.niki.controller;

import br.ufsm.csi.pi.niki.model.ContaUsuario;
import br.ufsm.csi.pi.niki.repository.RepositorioContaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerUsuario {

    @Autowired
    private PasswordEncoder passwordEncoder;

    final
    RepositorioContaUsuario repositorioContaUsuario;

    public ControllerUsuario(RepositorioContaUsuario repositorioContaUsuario) {
        this.repositorioContaUsuario = repositorioContaUsuario;
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<ContaUsuario>> getAllUsers() {
        try {
            List<ContaUsuario> users = new ArrayList<ContaUsuario>(repositorioContaUsuario.findAll());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{usuario_id}")
    public ResponseEntity<ContaUsuario> getUserById(@PathVariable("usuario_id") int usuario_id) {
        Optional<ContaUsuario> userData = repositorioContaUsuario.findById(usuario_id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/usuario")
    public ResponseEntity<ContaUsuario> addUsuario(@RequestBody ContaUsuario contaUsuario) {
        ContaUsuario _contaUsuario = repositorioContaUsuario.save(new ContaUsuario(
                contaUsuario.getUsuario_username(),
                contaUsuario.getUsuario_nome(),
                passwordEncoder.encode(contaUsuario.getUsuario_senha()),
                contaUsuario.isAdmin())
        );
        return new ResponseEntity<>(_contaUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/usuario/{usuario_id}")
    public ResponseEntity<ContaUsuario> editUsuario(@PathVariable("usuario_id") int usuario_id,
                                                    @RequestBody ContaUsuario contaUsuario) {
        Optional<ContaUsuario> userData = repositorioContaUsuario.findById(usuario_id);

        if (userData.isPresent()) {
            ContaUsuario _contaUsuario = userData.get();
            _contaUsuario.setUsuario_username(contaUsuario.getUsuario_username());
            _contaUsuario.setUsuario_nome(contaUsuario.getUsuario_nome());
            String encodedPassword = passwordEncoder.encode(contaUsuario.getUsuario_senha());
            _contaUsuario.setUsuario_senha(encodedPassword);

            return new ResponseEntity<>(repositorioContaUsuario.save(_contaUsuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuario/{usuario_id}")
    public ResponseEntity<ContaUsuario> deleteUserById(@PathVariable("usuario_id") int usuario_id) {

        try {
            repositorioContaUsuario.deleteById(usuario_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

