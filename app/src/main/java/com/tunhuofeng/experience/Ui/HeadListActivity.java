package com.tunhuofeng.experience.Ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.tunhuofeng.experience.MyAdapter.HeadImgAdapter;
import com.tunhuofeng.experience.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static java.security.AccessController.getContext;

public class HeadListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_list);
        List<String> headList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            headList.add("i:" + i);
        }
        headImgAdapter = new HeadImgAdapter(headList);
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setAdapter(headImgAdapter);
    }


    public void onClickDelete(View view) {
        headImgAdapter.remove(0);
    }

    private HeadImgAdapter headImgAdapter;

    private Disposable mSelfCheckDispose;

    @SuppressLint("AutoDispose")
    public void onClickStartAn(View view) {
        // 每隔几秒
        int period = 3;
        mSelfCheckDispose = Observable.interval(period, period, TimeUnit.SECONDS)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        return 0;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定运行在main线程
                .subscribe(new Consumer<Integer>() {
                               @Override
                               public void accept(Integer integer) throws Exception {
                                   String zeroString = headImgAdapter.getItem(integer);
                                   headImgAdapter.remove(integer);
                                   if (null != zeroString) {
                                       headImgAdapter.addData(zeroString);
                                   }
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mSelfCheckDispose) {
            mSelfCheckDispose.dispose();
        }
    }
}
