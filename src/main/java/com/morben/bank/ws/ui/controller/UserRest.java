package com.morben.bank.ws.ui.controller;

public class UserRest {

    private String userId;
    private String nome;
    private String password;
    //private String encryptedsenha;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
