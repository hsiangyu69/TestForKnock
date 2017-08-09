package com.hsiangyu.testforknock.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hsiangyu.testforknock.AppPreferences;
import com.hsiangyu.testforknock.R;
import com.hsiangyu.testforknock.api.KnockApi;
import com.hsiangyu.testforknock.api.response.Member;
import com.hsiangyu.testforknock.databinding.ActivityLoginBinding;

import java.util.List;

/**
 * Created by hsiangyuchen on 06/08/2017.
 */

public class LoginActivity extends AppCompatActivity {
    // Binding
    private ActivityLoginBinding binding;

    /* -------------------------- Life cycle */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding != null
                && binding.getViewModel() != null) {
            binding.getViewModel().destroy();
        }
    }

    /* ---------------------------------- Binding */

    /**
     * Initial binding
     */
    private void initBinding() {
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_login);
        binding.setModel(new LoginModel(AppPreferences.getInstance().isLogin()));
        binding.setViewModel(new LoginViewModel(this));
    }

    /* ---------------------------------- Data */

    private void initData() {
        if (AppPreferences.getInstance().isLogin()) {
            requestMember();
        }
    }

    /* -------------------------- API */

    /**
     * request login
     */
    public void requestLogin() {
        KnockApi.getInstance().requestLogin(new KnockApi.ApiCallback() {
            @Override
            public void succeed(@NonNull Object resultObject) {
                String token = (String) resultObject;
                AppPreferences.getInstance().setToken(token);
                AppPreferences.getInstance().setIsLogin(true);
                AppPreferences.getInstance().setName(binding.getModel().getName());
                AppPreferences.getInstance().setPwd(binding.getModel().getPwd());
                requestMember();
                binding.getModel().setViewIndex(LoginModel.VIEW_MEMBER);
            }

            @Override
            public void failed() {

            }
        }, this, binding.getModel().getName(), binding.getModel().getPwd());
    }

    /**
     * Request member
     */
    public void requestMember() {
        KnockApi.getInstance().requestMember(new KnockApi.ApiCallback() {
            @Override
            public void succeed(@NonNull Object resultObject) {
                List<Member> memberList = (List<Member>) resultObject;
                binding.recyclerView.setAdapter(new LoginAdapter(memberList));
            }

            @Override
            public void failed() {

            }
        }, this, AppPreferences.getInstance().getName(), AppPreferences.getInstance().getPwd(), AppPreferences.getInstance().getToken());
    }
}
