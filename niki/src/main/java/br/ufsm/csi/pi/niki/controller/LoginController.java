package br.ufsm.csi.pi.niki.controller;

import br.ufsm.csi.pi.niki.model.ContaUsuario;
import br.ufsm.csi.pi.niki.repository.RepositorioContaUsuario;
import br.ufsm.csi.pi.niki.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    final
    RepositorioContaUsuario repositorioContaUsuario;

    public LoginController(RepositorioContaUsuario repositorioContaUsuario) {
        this.repositorioContaUsuario = repositorioContaUsuario;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> auth(@RequestBody ContaUsuario contaUsuario) {
        System.out.println("Username: " + contaUsuario.getUsuario_username());
        System.out.println("Is_Admin:" + contaUsuario.isAdmin());

        try {
            final Authentication authenticaticon = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(contaUsuario.getUsuario_username(), contaUsuario.getUsuario_senha()));
            if (authenticaticon.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticaticon);

                System.out.println("*** Generating Authorization Token ***");
                String token = new JWTUtil().geraToken(contaUsuario.getUsuario_username());

                new ContaUsuario();
                ContaUsuario loggedUser;
                loggedUser = this.repositorioContaUsuario.findByUsername(contaUsuario.getUsuario_username());

                contaUsuario.setToken(token);
                contaUsuario.setSenha("");
                contaUsuario.setAdmin(loggedUser.isAdmin());
                System.out.println("Is_Admin:" + loggedUser.isAdmin());

                return new ResponseEntity<>(contaUsuario, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário ou senha incorretos", HttpStatus.BAD_REQUEST);
    }
}
