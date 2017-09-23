package com.simulando.joaopaulodribeiro.simulando.model.student;

/**
 * Created by joao.paulo.d.ribeiro on 23/09/2017.
 */

public class AuthStudentResponse {

    String status;
    Data data;
    String token;
    Long expire_at;

    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public String getToken() {
        return token;
    }

    public Long getExpire_at() {
        return expire_at;
    }

    /******************************************/
    public class Data {
        Integer id;
        String email;
        String password;
        String role;
        String name;
        Integer school_id;
        Student student;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getSchool_id() {
            return school_id;
        }

        public Student getStudent() {
            return student;
        }
    }
}
