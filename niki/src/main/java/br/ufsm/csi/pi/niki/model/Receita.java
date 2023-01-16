/*
Poderá Adicionar e esse são os campos:
- Nome;
- Imagem;
- Categoria (que será definida pelo programador já que será uma
tabela a parte, o administrador apenas poderá por qual o tipo da categoria e
não cadastrar uma categoria nova);
- Ingredientes;
- Modo de Preparo;
- Porções;
- Tempo de Preparo;
- Poderá Editar (poderá alterar todos os dados acima)
- Poderá Excluir;
 */

package br.ufsm.csi.pi.niki.model;

import javax.persistence.*;

@Entity
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int receita_id;


    @Column (name = "receita_nome", nullable = false, unique = true)
    private String receita_nome;

    @Column (name = "receita_categoria")
    private String receita_categoria;

    @Column (name = "receita_ingredientes")
    private String receita_ingredientes;

    @Column (name = "receita_mododepreparo")
    private String receita_mododepreparo;

    @Column (name = "receita_porcoes")
    private String receita_porcoes;

    @Column (name = "receita_tempodepreparo")
    private String receita_tempodepreparo;

    public int getReceita_id() {
        return receita_id;
    }

    public void setReceita_id(int receita_id) {
        this.receita_id = receita_id;
    }

    public String getReceita_nome() {
        return receita_nome;
    }

    public void setReceita_nome(String receita_nome) {
        this.receita_nome = receita_nome;
    }

    public String getReceita_categoria() {
        return receita_categoria;
    }

    public void setReceita_categoria(String receita_categoria) {
        this.receita_categoria = receita_categoria;
    }

    public String getReceita_ingredientes() {
        return receita_ingredientes;
    }

    public void setReceita_ingredientes(String receita_ingredientes) {
        this.receita_ingredientes = receita_ingredientes;
    }

    public String getReceita_mododepreparo() {
        return receita_mododepreparo;
    }

    public void setReceita_mododepreparo(String receita_mododepreparo) {
        this.receita_mododepreparo = receita_mododepreparo;
    }

    public String getReceita_porcoes() {
        return receita_porcoes;
    }

    public void setReceita_porcoes(String receita_porcoes) {
        this.receita_porcoes = receita_porcoes;
    }

    public String getReceita_tempodepreparo() {
        return receita_tempodepreparo;
    }

    public void setReceita_tempodepreparo(String receita_tempodepreparo) {
        this.receita_tempodepreparo = receita_tempodepreparo;
    }

    public Receita(String receita_nome, String receita_categoria, String receita_ingredientes, String receita_mododepreparo, String receita_porcoes, String receita_tempodepreparo) {
        this.receita_nome = receita_nome;
        this.receita_categoria = receita_categoria;
        this.receita_ingredientes = receita_ingredientes;
        this.receita_mododepreparo = receita_mododepreparo;
        this.receita_porcoes = receita_porcoes;
        this.receita_tempodepreparo = receita_tempodepreparo;
    }
    public Receita(){}



}
