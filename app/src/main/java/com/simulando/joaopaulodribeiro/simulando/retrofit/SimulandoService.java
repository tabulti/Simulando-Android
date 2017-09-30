package com.simulando.joaopaulodribeiro.simulando.retrofit;

import com.simulando.joaopaulodribeiro.simulando.model.simulates.ListSimulatesResponse;
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentBody;
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentResponse;
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentBody;
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by joao.paulo.d.ribeiro on 21/09/2017.
 */

public interface SimulandoService {

    interface RegisterStudent {
        void onRegisterStudent(RegisterStudentResponse res, Error err);
    }

    interface AuthStudent {
        void onAuthStudent(AuthStudentResponse res, Error err);
    }

    interface ListTests {
        void onListTests(ListSimulatesResponse res, Error err);
    }

    @POST("api/students")
    Call<RegisterStudentResponse> postStudent(@Body RegisterStudentBody body);

    @POST("api/authenticate")
    Call<AuthStudentResponse> authStudent(@Body AuthStudentBody body);

    @GET("api/tests")
    Call<ListSimulatesResponse> listSimulates(@Header("Authorization") String userToken);
}
