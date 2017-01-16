package com.certimais.API.User;

import com.certimais.API.APIConsts;

/**
 * Created by Luciano José on 10/01/2017.
 */

public class UserAPIConsts {

    public static final String USER_PATH = "alunos";
    public static final String USER_AUTH = "/auth";

    public static final String ENDPOINT_USER = APIConsts.BASE_URL + USER_PATH;
    public static final String ENDPOINT_AUTH_USER = APIConsts.BASE_URL + USER_PATH + USER_AUTH;

}
