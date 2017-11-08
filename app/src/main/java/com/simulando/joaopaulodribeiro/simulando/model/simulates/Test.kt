package com.simulando.joaopaulodribeiro.simulando.model.simulates

import java.io.Serializable

/**
 * Created by joao.paulo.d.ribeiro on 30/09/2017.
 */

class Test(var id: Int, val title: String, val description: String, val total_questions: String,
           val estimated_time: String, val test_start_date: String, val test_end_date: String,
           val created_at: String, val updated_at: String, val deleted_at: String,
           val questions: List<Question>) : Serializable