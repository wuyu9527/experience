package com.tunhuofeng.experience.Common.Base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by zlt on 2016/11/22.
 */

public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends Activity {

    protected T mPresenter; //Presenter 对象
    protected HashMap<String, View> allView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseHandler = new Handler();
        mPresenter = createPresenter();//创建Presenter
        mPresenter.attachView((V) this);
        allView = new HashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setAllView(allView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.detachView();
            }
        }, 1000);
    }

    protected abstract T createPresenter();

    protected void init() {

    }

    protected Handler baseHandler;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };


    /**
     * 结束Activity
     */
    protected void doFinish() {
        baseHandler.post(runnable);
    }

    /**
     * 调返回参数
     */
    protected void doFinish(int result) {
        setResult(result);
        finish();
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }
}
