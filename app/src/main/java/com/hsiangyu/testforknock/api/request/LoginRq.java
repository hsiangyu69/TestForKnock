package com.hsiangyu.testforknock.api.request;

import android.support.annotation.NonNull;

/**
 * Created by hsiangyuchen on 07/08/2017.
 */

public class LoginRq {
    private String name = "";
    private String pwd = "";

    public LoginRq(@NonNull String name,
                   @NonNull String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    /* ------------------------------ Getter */

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPwd() {
        return pwd;
    }
}
