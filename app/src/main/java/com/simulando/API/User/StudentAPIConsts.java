package com.simulando.API.User;

import com.simulando.API.APIConsts;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class StudentAPIConsts {

    public static final String STUDENT_PATH = "students/";
    public static final String STUDENT_AUTH = "auth";
    public static final String PROFILE_PATH = "profile";
    public static final String RANK_PATH = "rank";

    public static final String ENDPOINT_USER = APIConsts.BASE_URL + STUDENT_PATH;
    public static final String ENDPOINT_AUTH_USER = APIConsts.BASE_URL + STUDENT_PATH + STUDENT_AUTH;
    public static final String ENDPOINT_STUDENT_PROFILE = APIConsts.BASE_URL + STUDENT_PATH +
            APIConsts.PATH_PARAM + "/" + PROFILE_PATH;
    public static final String ENDPOINT_STUDENTS_RANK = APIConsts.BASE_URL + STUDENT_PATH + RANK_PATH;

}
