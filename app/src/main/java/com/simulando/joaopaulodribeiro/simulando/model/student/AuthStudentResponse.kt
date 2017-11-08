package com.simulando.joaopaulodribeiro.simulando.model.student

/**
 * Created by joao.paulo.d.ribeiro on 23/09/2017.
 */

class AuthStudentResponse {

    var status: String = ""
    var data: Data? = null
    var token: String = ""
    var expire_at: Long = -1

    inner class Data {
        var id: Int = -1
        var email: String = ""
        var password: String = ""
        var role: String = ""
        var name: String = ""
        var school_id: Int = -1
        var student: Student? = null
    }
}
