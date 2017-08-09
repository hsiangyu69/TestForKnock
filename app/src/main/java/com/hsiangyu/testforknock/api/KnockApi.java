package com.hsiangyu.testforknock.api;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hsiangyu.testforknock.AppPreferences;
import com.hsiangyu.testforknock.R;
import com.hsiangyu.testforknock.api.request.LoginRq;
import com.hsiangyu.testforknock.api.response.LoginRs;
import com.hsiangyu.testforknock.api.response.MemberRs;
import com.hsiangyu.testforknock.login.LoginActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hsiangyuchen on 07/08/2017.
 */

public class KnockApi {
    private static final String AUTHORIZATION = "Authorization";


    /* ------------------------------ Interface */

    public interface ApiCallback {
        void succeed(@NonNull Object resultObject) throws IOException;

        void failed();
    }

    // Instance
    private static KnockApi knockApi;

     /* ------------------------------ Instance */

    @NonNull
    public static synchronized KnockApi getInstance() {
        if (knockApi == null) {
            knockApi = new KnockApi();
        }
        return knockApi;
    }

    /* ------------------------------ Api request */

    /**
     * Request login
     *
     * @param context APP context
     * @param name    user name
     * @param pwd     user password
     */
    public void requestLogin(@NonNull final ApiCallback apiCallback,
                             @NonNull Context context,
                             @NonNull String name,
                             @NonNull String pwd) {
        Call<LoginRs> call = createService(context).login(new LoginRq(name, pwd));
        call.enqueue(new Callback<LoginRs>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<LoginRs> call,
                                   @NonNull Response<LoginRs> response) {
                try {
                    boolean succeed = response.code() == 200 && response.isSuccessful() && response.body() != null;
                    if (succeed) {
                        apiCallback.succeed(response.body().getToken().getToken());
                    } else {
                        apiCallback.failed();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginRs> call,
                                  @NonNull Throwable t) {
                apiCallback.failed();
            }
        });
    }

    /**
     * Request member
     *
     * @param apiCallback apiCallback
     * @param activity    context
     * @param token       app token
     */
    public void requestMember(@NonNull final ApiCallback apiCallback,
                              @NonNull Activity activity,
                              @NonNull String userName,
                              @NonNull String pwd,
                              @NonNull String token) {
        Call<MemberRs> call = createService(activity, userName, pwd, token).member(token);
        call.enqueue(new Callback<MemberRs>() {
            @Override
            public void onResponse(@NonNull Call<MemberRs> call,
                                   @NonNull Response<MemberRs> response) {
                try {
                    boolean succeed = response.code() == 200 && response.isSuccessful() && response.body() != null;
                    if (succeed) {
                        apiCallback.succeed(response.body().getData());
                    } else {
                        apiCallback.failed();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MemberRs> call,
                                  @NonNull Throwable t) {
                apiCallback.failed();

            }
        });
    }

    /* ------------------------------ Service */


    /**
     * Create service
     *
     * @param activity Target activity
     */
    private KnockService createService(@NonNull Activity activity,
                                       @NonNull String userName,
                                       @NonNull String pwd,
                                       @NonNull String authToken) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(activity.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create());

        // Read Timeout
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        // Timeout
        okHttpClientBuilder.readTimeout(200, TimeUnit.SECONDS);

        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(activity, userName, pwd, authToken);

            if (!okHttpClientBuilder.interceptors().contains(interceptor)) {
                okHttpClientBuilder.addInterceptor(interceptor);
            }
        }


        builder.client(okHttpClientBuilder.build());
        return builder.build().create(KnockService.class);
    }

    /**
     * Create K service
     *
     * @param context APP context
     */
    private KnockService createService(@NonNull Context context) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create());

        // Read Timeout
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        // Timeout
        okHttpClientBuilder.readTimeout(200, TimeUnit.SECONDS);

        builder.client(okHttpClientBuilder.build());
        return builder.build().create(KnockService.class);
    }

    /* ------------------------------ Interceptor */

    private class AuthenticationInterceptor implements Interceptor {

        private String authToken;
        private String userName;
        private String pwd;
        private Activity activity;


        /* ------------------------------ Constructor */

        /**
         * @param activity APP context
         * @param userName userName
         * @param pwd      user password
         * @param token    token
         */
        AuthenticationInterceptor(@NonNull Activity activity,
                                  @NonNull String userName,
                                  @NonNull String pwd,
                                  @NonNull String token) {
            this.activity = activity;
            this.userName = userName;
            this.pwd = pwd;
            this.authToken = token;
        }

        @Override
        public okhttp3.Response intercept(@NonNull final Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader(AUTHORIZATION, authToken)
                    .build();
            final okhttp3.Response[] response = {
                    chain.proceed(request)
            };

            if (!response[0].isSuccessful() && isForbidden(response[0].code())) {
                requestLogin(new ApiCallback() {
                    @Override
                    public void succeed(@NonNull final Object resultObject) throws IOException {
                        final String refreshToken = (String) resultObject;
                        AppPreferences.getInstance().setToken(refreshToken);
                        // Request member
                        if (activity != null
                                && activity instanceof LoginActivity) {
                            ((LoginActivity) activity).requestMember();
                        }
                    }

                    @Override
                    public void failed() {

                    }
                }, activity, userName, pwd);
            }

            return chain.proceed(request);
        }

        private boolean isForbidden(int code) {
            return code == HttpURLConnection.HTTP_FORBIDDEN;
        }
    }
}
