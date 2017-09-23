package com.simulando.joaopaulodribeiro.simulando.model.student;

/**
 * Created by joao.paulo.d.ribeiro on 23/09/2017.
 */

public class AuthStudentBody {
    String email;
    String password;

    public AuthStudentBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
