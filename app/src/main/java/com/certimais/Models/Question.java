package com.certimais.Models;

import android.support.v7.util.SortedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class Question {

    public String id;
    public String enunciado;
    public String resposta;
    public String ano;
    public String numeroQuestao;
    public String status;
    public List<Alternative> alternativa;

}
