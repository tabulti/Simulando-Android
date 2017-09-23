package com.simulando.joaopaulodribeiro.simulando.model.student;

/**
 * Created by joao.paulo.d.ribeiro on 21/09/2017.
 */

public class RegisterStudentBody {
    String name;
    String email;
    String password;

    public RegisterStudentBody(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
