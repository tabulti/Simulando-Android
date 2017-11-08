package com.simulando.joaopaulodribeiro.simulando.model.simulates

import android.content.Intent

import java.io.Serializable

/**
 * Created by joao.paulo.d.ribeiro on 02/10/2017.
 */

class Question constructor(id: Int, title: String, text: String, statement: String, reference: String,
               image_url: String, image_position: String, answer: String, year: Int,
               reward: Int, penalty: Int, question_site_url: String,
               question_site_id: String, subject_id: Int, alternatives: List<Alternative>) : Serializable {
    var id: Int = id
    var title: String = title
    var text: String = text
    var statement: String = statement
    var reference: String = reference
    var image_url: String = image_url
    var image_position: String = image_position
    var answer: String = answer
    var year: Int = year
    var reward: Int = reward
    var penalty: Int = penalty
    var question_site_url: String = question_site_url
    var question_site_id: String = question_site_id
    var subject_id: Int = subject_id
    var alternatives: List<Alternative> = alternatives


    inner class Alternative(id: Int, letter: String, text: String, question_id: Int) : Serializable {

        var id: Int = id
        var letter: String = letter
        var text: String = text
        var question_id: Int = question_id
    }
}
