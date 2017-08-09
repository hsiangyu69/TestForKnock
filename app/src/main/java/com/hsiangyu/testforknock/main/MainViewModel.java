package com.hsiangyu.testforknock.main;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.hsiangyu.testforknock.game.GameActivity;
import com.hsiangyu.testforknock.login.LoginActivity;

/**
 * Created by hsiangyuchen on 06/08/2017.
 */

public class MainViewModel {
    private Activity activity;

    MainViewModel(@NonNull Activity activity) {
        this.activity = activity;
    }

    /* ------------------------ On click */

    /**
     * on examination 1 click
     */
    public void onExamination1Click() {
        navigate(LoginActivity.class);
    }

    /**
     * on examination 2 click
     */
    public void onExamination2Click() {
        navigate(GameActivity .class);
    }

    /* ------------------------------ Navigation */

    /**
     * Navigate to target page
     *
     * @param cls target page
     */
    private void navigate(@NonNull Class cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

   /* ------------------------------ Destroy */

    /**
     * Decouple bidirectional reference
     */
    void destroy() {
        activity = null;
    }
}
