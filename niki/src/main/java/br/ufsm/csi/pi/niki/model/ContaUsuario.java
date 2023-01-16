package br.ufsm.csi.pi.niki.model;

import javax.persistence.*;

@Entity
@Table(name = "usuario_conta")
public class ContaUsuario {
        @Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int usuario_id;

@Column(name = "usuario_username", nullable = false, unique = true)
private String usuario_username;

        public String getUsuario_email() {
                return usuario_email;
        }

        public void setUsuario_email(String usuario_email) {
                this.usuario_email = usuario_email;
        }

        public String getUsuario_cpf() {
                return usuario_cpf;
        }

        public void setUsuario_cpf(String usuario_cpf) {
                this.usuario_cpf = usuario_cpf;
        }

        public String getUsuario_celular() {
                return usuario_celular;
        }

        public void setUsuario_celular(String usuario_celular) {
                this.usuario_celular = usuario_celular;
        }

        public String getUsuario_senha() {
                return usuario_senha;
        }

        public void setUsuario_senha(String usuario_senha) {
                this.usuario_senha = usuario_senha;
        }

        @Column(name = "usuario_email")
private String usuario_email;
@Column(name = "usuario_cpf")
private String usuario_cpf;

@Column(name = "usuario_celular")
private String usuario_celular;
@Column(name = "usuario_nome")
private String usuario_nome;

@Column(name = "usuario_senha")
private String usuario_senha;

@Column(name = "usuario_token")
public String usuario_token;

@Column(name = "is_admin")
public boolean isAdmin;

public ContaUsuario() {

        }

public ContaUsuario(String usuario_username,String usuario_celular, String usuario_cpf,
        String usuario_nome, String usuario_email,
        String usuario_senha, Boolean isAdmin) {
        this.usuario_username = usuario_username;
        this.usuario_celular = usuario_celular;
        this.usuario_cpf = usuario_cpf;
        this.usuario_nome = usuario_nome;
        this.usuario_email = usuario_email;
        this.usuario_senha = usuario_senha;
        this.isAdmin = isAdmin;
        }

        public ContaUsuario(String usuario_username, String usuario_nome, String encode, boolean admin) {
        }

public String getUsuario_username() {
        return usuario_username;
        }

public void setUsuario_username(String usuario_username) {
        this.usuario_username = usuario_username;
        }
public int getUsuario_id() {
        return usuario_id;
        }

public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
        }


public String getUsuario_nome() {
        return usuario_nome;
        }

public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
        }


        public boolean isAdmin() {
                return false;
        }

        public void setToken(String usuario_token) {
                this.usuario_token = usuario_token;
        }

        public void setSenha(String usuario_senha) {
                this.usuario_senha = usuario_senha;
        }

        public void setAdmin(boolean admin) {
        }
}

