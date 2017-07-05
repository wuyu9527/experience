package com.tunhuofeng.experience.Common.Base;

import android.content.Context;

import com.tunhuofeng.experience.MyApplication;


/**
 * Created by zlt on 2016/11/23.
 */

public interface BaseInterfaceFView<T> {
    void showSuccess(T t);
    void showFailure(String error);
    void outUser();
    MyApplication getApp();
    String getDEVICE();
    Context getContext();
}
