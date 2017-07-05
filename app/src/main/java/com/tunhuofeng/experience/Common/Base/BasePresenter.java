package com.tunhuofeng.experience.Common.Base;

import android.view.View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * 利用弱引用，避免使用mvp时，发生内存泄漏的基类P
 * Created by zlt on 2016/11/22.
 */

public abstract class BasePresenter<T> {

    protected String sort = ""; //按字段搜索
    protected String order = "DESC";//DESC 降序  ASC 升序
    protected int limit = 20;
    protected int offset = 0;
    protected String v = "";//view 条件
    protected String keyword = "";//搜索关键字 条件
    protected String store_id = "";

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    protected HashMap<String,View> allView;//所有View

    public HashMap<String, View> getAllView() {
        return allView;
    }

    public void setAllView(HashMap<String, View> allView) {
        this.allView = allView;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    protected Reference<T> mViewRef;//View接口类型的弱引用
    private T view;
    public void attachView(T view) {
        this.view=view;
        mViewRef = new WeakReference<T>(view);//建立关系
    }

    protected T getView(){
        if (isViewAttached()) {
            return mViewRef.get();
        }else {
            if (mViewRef==null){
                mViewRef = new WeakReference<T>(view);//建立关系
            }
            if (isViewAttached()){
                return mViewRef.get();
            }else {
                return null;
            }
        }
    }

    /**
     * [绑定控件]
     *
     * @param view 传入view
     * @return
     */
    protected <T extends View> T $(View view) {
        return (T)view;
    }

    public boolean isViewAttached(){
        return mViewRef !=null && mViewRef.get() !=null;
    }

    public void detachView(){
        if(mViewRef !=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
