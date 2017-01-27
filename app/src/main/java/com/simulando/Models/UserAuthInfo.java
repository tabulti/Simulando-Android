package com.simulando.Models;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class UserAuthInfo {

    public final boolean social;
    public final String nome;
    public final String cpf;
    public final String email;
    public final String senha;
    public final String photo;

    public UserAuthInfo(boolean social, String nome, String cpf, String email, String senha, String photo) {
        this.social = social;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserAuthInfo{" +
                "social=" + social +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
