package com.simulando.joaopaulodribeiro.simulando.retrofit;

import android.accounts.AccountManager;
import android.support.annotation.NonNull;

import com.simulando.joaopaulodribeiro.simulando.model.simulates.FindSimulateByIdResponse;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.ListSimulatesResponse;
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentBody;
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentResponse;
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentBody;
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentResponse;
import com.simulando.joaopaulodribeiro.simulando.utils.Utils;

import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joao.paulo.d.ribeiro on 21/09/2017.
 */

public class RetrofitImplementation {

    private static RetrofitImplementation _instance_ = null;
    private SimulandoService service = null;


    private RetrofitImplementation(){

    }

    public static RetrofitImplementation getInstance(){
        if (_instance_ == null) {
            _instance_ = new RetrofitImplementation();
        }
        return _instance_;
    }


    public void initRetrofit(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simulandoapp.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .client(client)
                .build();



        service = retrofit.create(SimulandoService.class);
    }


    public void registerStudent(RegisterStudentBody body, final SimulandoService.RegisterStudent handler) {

        if (service != null) {

            final Call<RegisterStudentResponse> res = service.postStudent(body);

            res.enqueue(new Callback<RegisterStudentResponse>() {
                @Override
                public void onResponse(Call<RegisterStudentResponse> call, Response<RegisterStudentResponse> response) {
                    if(response != null) {
                        if (response.errorBody() == null && response.body() != null){
                            handler.onRegisterStudent(response.body(), null);
                        } else {
                            String err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message();
                            handler.onRegisterStudent(null, new Error(err));

                        }
                    }
                }

                @Override
                public void onFailure(Call<RegisterStudentResponse> call, Throwable t) {
                    if (t != null) {
                        handler.onRegisterStudent(null, new Error(t.toString()));
                    }
                }
            });
        }
    }

    public void authStudent(AuthStudentBody body, final SimulandoService.AuthStudent handler) {
        if (service != null) {
            final Call<AuthStudentResponse> res = service.authStudent(body);
            res.enqueue(new Callback<AuthStudentResponse>() {
                @Override
                public void onResponse(Call<AuthStudentResponse> call, Response<AuthStudentResponse> response) {
                    if(response != null) {
                        if (response.errorBody() == null && response.body() != null){
                            handler.onAuthStudent(response.body(), null);
                        } else {
                            String err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message();
                            handler.onAuthStudent(null, new Error(err));

                        }
                    }
                }
                @Override
                public void onFailure(Call<AuthStudentResponse> call, Throwable t) {
                    if (t != null) {
                        handler.onAuthStudent(null, new Error(t.toString()));
                    }
                }
            });
        }
    }

    public void listTests(@NonNull String userToken, final SimulandoService.ListTests handler) {
        if (service != null) {
            final Call<ListSimulatesResponse> res = service.listSimulates(userToken);

            res.enqueue(new Callback<ListSimulatesResponse>() {
                @Override
                public void onResponse(Call<ListSimulatesResponse> call, Response<ListSimulatesResponse> response) {
                    if(res != null) {
                        if (response.errorBody() == null && response.body() != null){
                            handler.onListTests(response.body(), null);
                        } else {
                            String err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message();
                            handler.onListTests(null, new Error(err));

                        }
                    }
                }

                @Override
                public void onFailure(Call<ListSimulatesResponse> call, Throwable t) {
                    if (t != null) {
                        handler.onListTests(null, new Error(t.toString()));
                    }
                }
            });
        }
    }

    public void findTestById(@NonNull String userToken, int id, final SimulandoService.FindSimulateById handler) {
        if (service != null) {
            final Call<FindSimulateByIdResponse> res = service.findSimulateById(userToken, id);

            res.enqueue(new Callback<FindSimulateByIdResponse>() {
                @Override
                public void onResponse(Call<FindSimulateByIdResponse> call, Response<FindSimulateByIdResponse> response) {
                    if(res != null) {
                        if (response.errorBody() == null && response.body() != null){
                            handler.onFindSimulateById(response.body(), null);
                        } else {
                            String err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message();
                            handler.onFindSimulateById(null, new Error(err));

                        }
                    }
                }

                @Override
                public void onFailure(Call<FindSimulateByIdResponse> call, Throwable t) {
                    if (t != null) {
                        handler.onFindSimulateById(null, new Error(t.toString()));
                    }
                }
            });
        }
    }


}
