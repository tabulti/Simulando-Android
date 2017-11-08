package com.simulando.joaopaulodribeiro.simulando.retrofit

import com.simulando.joaopaulodribeiro.simulando.model.simulates.FindSimulateByIdResponse
import com.simulando.joaopaulodribeiro.simulando.model.simulates.ListSimulatesResponse
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentBody
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentResponse
import com.simulando.joaopaulodribeiro.simulando.model.student.RefreshTokenResponse
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentBody
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentResponse

import java.util.concurrent.Executors

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by joao.paulo.d.ribeiro on 21/09/2017.
 */

class RetrofitImplementation private constructor() {
    private var service: SimulandoService? = null


    fun initRetrofit() {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://simulandoapp.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .client(client)
                .build()



        service = retrofit.create(SimulandoService::class.java)
    }


    fun registerStudent(body: RegisterStudentBody, handler: SimulandoService.RegisterStudent) {

        if (service != null) {

            val res = service!!.postStudent(body)

            res.enqueue(object : Callback<RegisterStudentResponse> {
                override fun onResponse(call: Call<RegisterStudentResponse>, response: Response<RegisterStudentResponse>?) {
                    if (response != null) {
                        if (response.errorBody() == null && response.body() != null) {
                            handler.onRegisterStudent(response.body(), null)
                        } else {
                            val err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message()
                            handler.onRegisterStudent(null, Error(err))

                        }
                    }
                }

                override fun onFailure(call: Call<RegisterStudentResponse>, t: Throwable?) {
                    if (t != null) {
                        handler.onRegisterStudent(null, Error(t.toString()))
                    }
                }
            })
        }
    }

    fun authStudent(body: AuthStudentBody, handler: SimulandoService.AuthStudent) {
        if (service != null) {
            val res = service!!.authStudent(body)
            res.enqueue(object : Callback<AuthStudentResponse> {
                override fun onResponse(call: Call<AuthStudentResponse>, response: Response<AuthStudentResponse>?) {
                    if (response != null) {
                        if (response.errorBody() == null && response.body() != null) {
                            handler.onAuthStudent(response.body(), null)
                        } else {
                            val err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message()
                            handler.onAuthStudent(null, Error(err))

                        }
                    }
                }

                override fun onFailure(call: Call<AuthStudentResponse>, t: Throwable?) {
                    if (t != null) {
                        handler.onAuthStudent(null, Error(t.toString()))
                    }
                }
            })
        }
    }

    fun listTests(userToken: String, handler: SimulandoService.ListTests) {
        if (service != null) {
            val res = service!!.listSimulates(userToken)

            res!!.enqueue(object : Callback<ListSimulatesResponse> {
                override fun onResponse(call: Call<ListSimulatesResponse>, response: Response<ListSimulatesResponse>) {
                    if (res != null) {
                        if (response.errorBody() == null && response.body() != null) {
                            handler.onListTests(response.body(), null)
                        } else {
                            val err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message()
                            handler.onListTests(null, Error(err))

                        }
                    }
                }

                override fun onFailure(call: Call<ListSimulatesResponse>, t: Throwable?) {
                    if (t != null) {
                        handler.onListTests(null, Error(t.toString()))
                    }
                }
            })
        }
    }

    fun findTestById(userToken: String, id: Int, handler: SimulandoService.FindSimulateById) {
        if (service != null) {
            val res = service!!.findSimulateById(userToken, id)

            res!!.enqueue(object : Callback<FindSimulateByIdResponse> {
                override fun onResponse(call: Call<FindSimulateByIdResponse>, response: Response<FindSimulateByIdResponse>) {
                    if (res != null) {
                        if (response.errorBody() == null && response.body() != null) {
                            handler.onFindSimulateById(response.body(), null)
                        } else {
                            val err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message()
                            handler.onFindSimulateById(null, Error(err))

                        }
                    }
                }

                override fun onFailure(call: Call<FindSimulateByIdResponse>, t: Throwable?) {
                    if (t != null) {
                        handler.onFindSimulateById(null, Error(t.toString()))
                    }
                }
            })
        }
    }

    fun refreshToken(userToken: String, handler: SimulandoService.RefreshToken) {
        if (service != null) {
            val res = service!!.refreshToken(userToken)

            res!!.enqueue(object : Callback<RefreshTokenResponse> {
                override fun onResponse(call: Call<RefreshTokenResponse>, response: Response<RefreshTokenResponse>) {
                    if (res != null) {
                        if (response.errorBody() == null && response.body() != null) {
                            handler.onRefreshToken(response.body(), null)
                        } else {
                            val err = "Code: " + response.raw().code() + "\n" +
                                    "Message: " + response.raw().message()
                            handler.onRefreshToken(null, Error(err))

                        }
                    }
                }

                override fun onFailure(call: Call<RefreshTokenResponse>, t: Throwable?) {
                    if (t != null) {
                        handler.onRefreshToken(null, Error(t.toString()))
                    }
                }
            })
        }
    }

    companion object {

        private var _instance_: RetrofitImplementation? = null

        val instance: RetrofitImplementation
            get() {
                if (_instance_ == null) {
                    _instance_ = RetrofitImplementation()
                }
                return _instance_!!
            }
    }

}
