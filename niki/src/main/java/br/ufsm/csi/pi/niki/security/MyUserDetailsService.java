package br.ufsm.csi.pi.niki.security;

import br.ufsm.csi.pi.niki.model.ContaUsuario;
import br.ufsm.csi.pi.niki.repository.RepositorioContaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private RepositorioContaUsuario repositorioContaUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username: "+username);

        ContaUsuario contaUsuario = repositorioContaUsuario.findByUsername(username);

        System.out.println(contaUsuario);

        if (contaUsuario == null) {
            throw new UsernameNotFoundException("Username or Password Not Found");
        }
        else {
            String authtority = "";
            if (contaUsuario.isAdmin == true) {
                authtority = "ADMIN";
            } else if (contaUsuario.isAdmin == false) {
                authtority = "USER";
            }
            System.out.println(authtority);
            UserDetails userDetails = User.withUsername(contaUsuario.getUsuario_username())
                    .password(contaUsuario.getUsuario_senha())
                    .authorities(authtority)
                    .build();

            return userDetails;
        }
    }
}
