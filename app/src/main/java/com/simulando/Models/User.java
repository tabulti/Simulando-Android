package com.simulando.Models;

/**
 * Created by Luciano José on 18/12/2016.
 */

public class User {

    public String id;
    public String nome;
    public String email;
    public String cpf;
    public String aluno_id;
    public String photo;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", aluno_id='" + aluno_id + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
