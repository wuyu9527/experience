package com.tunhuofeng.experience.Common.Base;

/**
 * Created by zlt on 2016/11/22.
 */

public interface OnBaseListener<T> {
    void onBaseSuccess(T t);
    void onBaseFailed();
}
