package com.simulando.joaopaulodribeiro.simulando.model.student

import com.simulando.joaopaulodribeiro.simulando.model.User

/**
 * Created by joao.paulo.d.ribeiro on 21/09/2017.
 */

class RegisterStudentResponse {
    var status: String = ""
    var data: Data? = null
    var token: String = ""
    var expire_at: Long = -1

    inner class Data {
        var id: Int = -1
        var name: String = ""
        var school_id: Int = -1
        var user: User? = null
        var updated_at: String = ""
        var created_at: String = ""
        var deleted_at: String = ""
        var user_id: Int = -1
    }
}
