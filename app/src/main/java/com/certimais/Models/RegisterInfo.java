package com.certimais.Models;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class RegisterInfo {

    public final String nome;
    public final String cpf;
    public final String email;
    public final String senha;

    public RegisterInfo(String nome, String cpf, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }
}
