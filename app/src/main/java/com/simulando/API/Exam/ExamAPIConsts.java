package com.simulando.API.Exam;

import com.simulando.API.APIConsts;

/**
 * Created by Luciano José on 31/01/2017.
 */

public class ExamAPIConsts {

    public static String EXAM_PATH = "exams/";
    public static String RESULT_PATH = "result";

    public static String ENDPOINT_EXAM_INFO = APIConsts.BASE_URL + EXAM_PATH + APIConsts.PATH_PARAM;
    public static String ENDPOINT_EXAM_RESULT = APIConsts.BASE_URL + EXAM_PATH +
            APIConsts.PATH_PARAM + "/result";
    public static String ENDPOINT_CANCEL_EXAM = APIConsts.BASE_URL + EXAM_PATH +
            APIConsts.PATH_PARAM + "/cancel";
    public static String ENDPOINT_EXAMS = APIConsts.BASE_URL + EXAM_PATH;
    public static String ENDPOINT_ANSWER_EXAM = APIConsts.BASE_URL + EXAM_PATH + "answer";

}
