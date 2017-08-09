package com.hsiangyu.testforknock.api.response;

import android.support.annotation.NonNull;

/**
 * Created by hsiangyuchen on 07/08/2017.
 */

public class LoginRs {
    private Token token = new Token();

    /* ------------------------------ Getter && Setter */

    @NonNull
    public Token getToken() {
        return token;
    }
}
