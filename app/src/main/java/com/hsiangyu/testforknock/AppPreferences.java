package com.hsiangyu.testforknock;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by hsiangyuchen on 06/08/2017.
 */

public class AppPreferences {
    private static final String PREF_IS_LOGIN = "PREF_IS_LOGIN";
    private static final String PREF_TOKEN = "PREF_TOKEN";
    private static final String PREF_NAME = "PREF_NAME";
    private static final String PREF_PWD = "PREF_PWD";

    // Main Preference Key
    private static final String PREF = "TEST_FOR_KNOCK";

    // Preference
    private SharedPreferences sharedPreferences;

    // Instance
    private static AppPreferences appPreferences;


    /* ------------------------------ Initial */

    /**
     * @param context APP context
     */
    public static synchronized void initialize(@NonNull Context context) {
        if (appPreferences != null) {
            throw new IllegalStateException("Extra call to initialize analytics trackers");
        }

        appPreferences = new AppPreferences(context);
    }

    /**
     * @param context APP context
     */
    private AppPreferences(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    /* ------------------------------ Instance */

    @NonNull
    public static synchronized AppPreferences getInstance() {
        if (appPreferences == null) {
            throw new IllegalStateException("Call initialize() before getInstance()");
        }

        return appPreferences;
    }

    /* ------------------------------ Token */

    public void setToken(@NonNull String token) {
        sharedPreferences.edit().putString(PREF_TOKEN, token).apply();
    }

    @NonNull
    public String getToken() {
        return sharedPreferences.getString(PREF_TOKEN, "");
    }

    /* ------------------------------ Login */

    public void setIsLogin(@NonNull boolean login) {
        sharedPreferences.edit().putBoolean(PREF_IS_LOGIN, login).apply();
    }

    @NonNull
    public boolean isLogin() {
        return sharedPreferences.getBoolean(PREF_IS_LOGIN, false);
    }


    @NonNull
    public String getName() {
        return sharedPreferences.getString(PREF_NAME, "");
    }

    public void setName(@NonNull String name) {
        sharedPreferences.edit().putString(PREF_NAME, name).apply();
    }

    @NonNull
    public String getPwd() {
        return sharedPreferences.getString(PREF_PWD, "");
    }

    public void setPwd(@NonNull String pwd) {
        sharedPreferences.edit().putString(PREF_PWD, pwd).apply();
    }
}
