package com.tunhuofeng.experience.Common.Base;

import android.content.Context;

import com.tunhuofeng.experience.MyApplication;


/**
 * Created by zlt on 2016/11/23.
 */

public interface BaseInterfaceView<T> {
    void showSuccess(T t);
    void showFailure(String error);
    MyApplication getApp();
    String getDEVICE();
    void outUser();
    Context getContext();
}
