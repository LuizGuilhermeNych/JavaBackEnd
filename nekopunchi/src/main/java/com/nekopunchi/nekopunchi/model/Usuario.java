package com.nekopunchi.nekopunchi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

//#region Atributos
    @Id //Vai tranformar essa coluna em uma PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
//#endregion

//#region Get e Set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
//#endregion

}