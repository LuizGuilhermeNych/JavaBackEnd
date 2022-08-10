package com.nekopunchi.nekopunchi.view.model;

public class UsuarioRequest {

//#region Atributos
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
