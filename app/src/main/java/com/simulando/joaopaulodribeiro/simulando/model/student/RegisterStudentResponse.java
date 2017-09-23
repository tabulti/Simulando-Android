package com.simulando.joaopaulodribeiro.simulando.model.student;

import com.simulando.joaopaulodribeiro.simulando.model.User;

/**
 * Created by joao.paulo.d.ribeiro on 21/09/2017.
 */

public class RegisterStudentResponse {
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
        String name;
        Integer school_id;
        User user;
        String updated_at;
        String created_at;
        String deleted_at;
        Integer user_id;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getSchool_id() {
            return school_id;
        }

        public User getUser() {
            return user;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getDeleted_at() {
            return deleted_at;
        }

        public Integer getUser_id() {
            return user_id;
        }
    }
}
