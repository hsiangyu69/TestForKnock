package com.hsiangyu.testforknock.api.response;

import android.support.annotation.NonNull;

/**
 * Created by hsiangyuchen on 07/08/2017.
 */

public class Token {
    private String name = "";
    private String iat = "";
    private String exp = "";
    private String token = "";

    public Token(){

    }

    /* ------------------------------ Getter */

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(@NonNull String iat) {
        this.iat = iat;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(@NonNull String exp) {
        this.exp = exp;
    }

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
    }
}
