package com.hsiangyu.testforknock;

import android.app.Application;

/**
 * Created by hsiangyuchen on 06/08/2017.
 */

public class TestForKnockApplication extends Application {

    /* ---------------------------------- Create */

    @Override
    public void onCreate() {
        super.onCreate();
        AppPreferences.initialize(this);
    }
}
