package com.hsiangyu.testforknock.api.response;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsiangyuchen on 08/08/2017.
 */

public class MemberRs {
    private List<Member> data = new ArrayList<>();


    /* ------------------------------ Getter */

    @NonNull
    public List<Member> getData() {
        return data;
    }
}
