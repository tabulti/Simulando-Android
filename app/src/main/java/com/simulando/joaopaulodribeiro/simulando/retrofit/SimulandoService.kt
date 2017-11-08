package com.simulando.joaopaulodribeiro.simulando.retrofit

import com.simulando.joaopaulodribeiro.simulando.model.simulates.FindSimulateByIdResponse
import com.simulando.joaopaulodribeiro.simulando.model.simulates.ListSimulatesResponse
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentBody
import com.simulando.joaopaulodribeiro.simulando.model.student.AuthStudentResponse
import com.simulando.joaopaulodribeiro.simulando.model.student.RefreshTokenResponse
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentBody
import com.simulando.joaopaulodribeiro.simulando.model.student.RegisterStudentResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by joao.paulo.d.ribeiro on 21/09/2017.
 */

interface SimulandoService {

    interface RegisterStudent {
        fun onRegisterStudent(res: RegisterStudentResponse?, err: Error?)
    }

    interface AuthStudent {
        fun onAuthStudent(res: AuthStudentResponse?, err: Error?)
    }

    interface ListTests {
        fun onListTests(res: ListSimulatesResponse?, err: Error?)
    }

    interface FindSimulateById {
        fun onFindSimulateById(res: FindSimulateByIdResponse?, err: Error?)
    }

    interface RefreshToken {
        fun onRefreshToken(res: RefreshTokenResponse?, err: Error?)
    }

    @POST("api/students")
    fun postStudent(@Body body: RegisterStudentBody): Call<RegisterStudentResponse>

    @POST("api/authenticate")
    fun authStudent(@Body body: AuthStudentBody): Call<AuthStudentResponse>

    @POST("api/refresh")
    fun refreshToken(@Header("Authorization") userToken: String): Call<RefreshTokenResponse>

    @GET("api/tests")
    fun listSimulates(@Header("Authorization") userToken: String): Call<ListSimulatesResponse>

    @GET("api/tests/{id}")
    fun findSimulateById(@Header("Authorization") userToken: String,
                         @Path("id") id: Int): Call<FindSimulateByIdResponse>
}
