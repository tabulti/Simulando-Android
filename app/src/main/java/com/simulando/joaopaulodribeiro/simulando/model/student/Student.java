package com.simulando.joaopaulodribeiro.simulando.model.student;

/**
 * Created by joao.paulo.d.ribeiro on 23/09/2017.
 */

public class Student {
    Integer id;
    String name;
    String created_at;
    String updated_at;
    String deleted_at;
    String school_id;
    Integer user_id;

    public Student(Integer id, String name, String created_at, String updated_at, String deleted_at,
                   String school_id, Integer user_id) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.school_id = school_id;
        this.user_id = user_id;
    }
}
