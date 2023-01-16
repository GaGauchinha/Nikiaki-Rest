package br.ufsm.csi.pi.niki.repository;

import br.ufsm.csi.pi.niki.model.ContaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface RepositorioContaUsuario extends JpaRepository<ContaUsuario, Integer> {

    @Query(value = "SELECT *  FROM  usuario_conta WHERE usuario_username = ?1", nativeQuery = true)
    ContaUsuario findByUsername(String usuario_username);

//    public default UserAccount findByUsername(String username) {
//        if(username.equals("natty")) {
//            return new UserAccount("Nathalia","natty", new BCryptPasswordEncoder().encode("123"), true);
//        } else {
//            return null;
//        }
//    }
}

