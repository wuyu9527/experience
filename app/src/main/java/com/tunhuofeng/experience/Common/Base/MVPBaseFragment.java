package com.tunhuofeng.experience.Common.Base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.widget.Toast;

/**
 * Created by zlt on 2016/11/22.
 */

public abstract class MVPBaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter; //Presenter 对象

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= createPresenter();//创建Presenter
        mPresenter.attachView((V) this);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

    /**
     * [简化Toast]
     * @param msg
     */
    protected void showToast(String msg){
        if (getContext()!=null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
