package com.certimais.API.Questions;

import com.certimais.API.APIConsts;

/**
 * Created by Luciano José on 08/01/2017.
 */

public class QuestionsAPIConsts {

    public static final String QUESTIONS_PATH = "perguntas";
    public static final String QUERY_RANDOM = "random=1";
    public static final String QUERY_AREA = "area=%s";
    public static final String QUERY_SUBJECT = "materia=%s";
    public static final String QUERY_YEAR = "ano=%s";

    public static final String ENDPOINT_RANDOM_QUESTION = APIConsts.BASE_URL + QUESTIONS_PATH + "?" + QUERY_RANDOM;


}
