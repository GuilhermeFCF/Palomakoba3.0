package com.example.listadecontatos.model;

import java.io.Serializable;

public class Contato implements Serializable {

    private String nome;
    private String email;
    private String telefone;
    private long id;

    public Contato(String nome, String email, String telefone, long id) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.id = id;
    }
    public Contato(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
