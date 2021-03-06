package br.com.agenda.agenda.models;

import java.io.Serializable;

public class Alunos implements Serializable {
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private Double nota;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
