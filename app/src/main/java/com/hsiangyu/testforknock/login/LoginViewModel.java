package com.hsiangyu.testforknock.login;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Editable;

/**
 * Created by hsiangyuchen on 07/08/2017.
 */

public class LoginViewModel {
    private Activity activity;

    /* ------------------------------ Constructor */

    public LoginViewModel(@NonNull Activity activity) {
        this.activity = activity;

    }

    /* ------------------------------ Input field */

    /**
     * Observe name field has text changed
     *
     * @param editable editable for name field
     * @param model    login model
     */
    public void nameTextChanged(@NonNull Editable editable,
                                @NonNull LoginModel model) {
        model.setName(editable.toString());
    }

    /**
     * Observe pwd field has text changed
     *
     * @param editable editable for pwd field
     * @param model    login model
     */
    public void pwdTextChanged(@NonNull Editable editable,
                               @NonNull LoginModel model) {
        model.setPwd(editable.toString());
    }

    /* ------------------------------ On Login Click */


    public void onLoginClick() {
        if (activity != null
                && activity instanceof LoginActivity) {
            ((LoginActivity) activity).requestLogin();
        }
    }

    /* ------------------------------ Destroy */

    /**
     * Decouple bidirectional reference
     */
    void destroy() {
        activity = null;
    }
}
