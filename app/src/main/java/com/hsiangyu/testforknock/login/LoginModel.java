package com.hsiangyu.testforknock.login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.android.databinding.library.baseAdapters.BR;


/**
 * Created by hsiangyuchen on 07/08/2017.
 */

public class LoginModel extends BaseObservable {
    public static final int VIEW_LOGIN = 0;
    public static final int VIEW_MEMBER = 1;
    private String name = "";
    private String pwd = "";
    private int viewIndex;

    public LoginModel(boolean login) {
        setViewIndex(login ? VIEW_MEMBER : VIEW_LOGIN);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(@NonNull String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }

    @Bindable
    public int getViewIndex() {
        return viewIndex;
    }

    public void setViewIndex(int viewIndex) {
        this.viewIndex = viewIndex;
        notifyPropertyChanged(BR.viewIndex);
    }

}
