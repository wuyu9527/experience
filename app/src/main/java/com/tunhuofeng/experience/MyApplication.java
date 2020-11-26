package com.tunhuofeng.experience;

import android.app.Application;
import android.util.Log;

import io.flutter.app.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

/**
 * Created by android on 2017/7/5.
 */

public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        initFlutter();
    }

    FlutterEngine flutterEngine;

    public FlutterEngine getFlutterEngine() {
        return flutterEngine;
    }

    private void initFlutter() {
        // Instantiate a FlutterEngine.
        flutterEngine = new FlutterEngine(this);
        // Configure an initial route.
//        flutterEngine.getNavigationChannel().setInitialRoute("your/route/here");
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        //方法一
//        initEventChannel();
        //方法二
//        initMethodChannel();
        //方法三
//        initBasicMessageChannel();
//        Log.i("flutter2Android", "initFlutter");
        // Cache the FlutterEngine to be used by FlutterActivity.
        // FlutterActivity中使用已经缓存的flutterEngine实例
        FlutterEngineCache
                .getInstance()
                .put("flutterEngine", flutterEngine);

    }
}

