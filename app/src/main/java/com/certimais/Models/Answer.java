package com.certimais.Models;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class Answer {

    public String alternativa_id;
    public String resposta;
    public long tempoGasto;

    public Answer(String alternativa_id, String resposta, long tempoGasto) {
        this.alternativa_id = alternativa_id;
        this.resposta = resposta;
        this.tempoGasto = tempoGasto;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "alternativaId='" + alternativa_id + '\'' +
                ", resposta='" + resposta + '\'' +
                ", tempoGasto=" + tempoGasto +
                '}';
    }
}

