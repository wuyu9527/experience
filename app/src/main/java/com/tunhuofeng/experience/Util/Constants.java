package com.tunhuofeng.experience.Util;

import android.os.Environment;

/**
 * Created by zlt on 2016/11/15.
 * 常量类
 */

public final class Constants {
    /**
     * 系统初始化配置文件名
     */
    public static final String SYSTEM_INIT_FILE_NAME = "sysini";

    /**
     * 本地缓存目录
     */
    public static final String CACHE_DIR;

    /**
     * 图片缓存目录
     */
    public static final String CACHE_DIR_IMAGE;
    /**
     * 版本安装包地址
     */
    public static final String CACHE_DIR_VERSION;

    /**
     *
     */
    public static final String CACHE_DIR_TEXT;

    /**
     * 网络请求超时
     */
    public static final int REQ_TIMEOUT = 3500;
    /**
     * 图片上传时间
     */
    public static final int UPLOAD_TIMEOUT = 15000;

    /**
     * 错误提示
     */
    public static final String NETWORK_ERROR = "网络未连接";

    /**
     * 列表分页请求个数
     */
    public static final int LIMIT_20 = 20;
    public static final int LIMIT_8 = 8;
    public static final int LIMIT_6 = 6;
    public static final int LIMIT_3 = 3;
    public static final int LIMIT_1 = 1;

    public static final int REQUEST_ENABLE_BT = 2;//开启蓝牙

    static {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyTest/";
        } else {
            CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/MyTest/";
        }
        CACHE_DIR_IMAGE = CACHE_DIR + "pic";
        CACHE_DIR_VERSION = CACHE_DIR + "version";
        CACHE_DIR_TEXT = CACHE_DIR + "MyText";
    }


}

