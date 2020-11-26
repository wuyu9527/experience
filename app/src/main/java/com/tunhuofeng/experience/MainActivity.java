package com.tunhuofeng.experience;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tunhuofeng.experience.Ui.HeadListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.common.StringCodec;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        initFlutter();
    }

    Button button;

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    FlutterEngine flutterEngine;


    private void initFlutter() {
        // Instantiate a FlutterEngine.
//        flutterEngine = new FlutterEngine(this);
        // Configure an initial route.
//        flutterEngine.getNavigationChannel().setInitialRoute("your/route/here");
        // Start executing Dart code to pre-warm the FlutterEngine.
//        flutterEngine.getDartExecutor().executeDartEntrypoint(
//                DartExecutor.DartEntrypoint.createDefault()
//        );
        flutterEngine = ((MyApplication) getApplication()).getFlutterEngine();
        //方法一
        initEventChannel();
        //方法二
        initMethodChannel();
        //方法三
        initBasicMessageChannel();
        Log.i("flutter2Android", "initFlutter");
        // Cache the FlutterEngine to be used by FlutterActivity.
        // FlutterActivity中使用已经缓存的flutterEngine实例
//        FlutterEngineCache
//                .getInstance()
//                .put("flutterEngine", flutterEngine);

    }

    public static void handleToast(Context context, MethodCall methodCall) {
        String msg = methodCall.argument("msg");
        int type = methodCall.argument("type");
        Toast.makeText(context, msg, type).show();
    }

    public void onClickHeadList(View view) {
//        startActivity(HeadListActivity.class);
        //简单调用
//        startActivity(
//                FlutterActivity.createDefaultIntent(this)
//        );

        //调用不同路径的
//        startActivity(
//                FlutterActivity
//                        .withNewEngine()
//                        .initialRoute("/my_route")
//                        .build(this)
//        );

        FlutterActivity.CachedEngineIntentBuilder builder =
                FlutterActivity.withCachedEngine("flutterEngine");
        FlutterActivity.NewEngineIntentBuilder builder1 = FlutterActivity.withNewEngine();
        startActivity(builder.build(this));

    }

    /**
     * 方法一:广播监听
     */
    private EventChannel eventChannel;

    private void initEventChannel() {
        eventChannel = new EventChannel(flutterEngine.getDartExecutor(), "event2Flutter");
        eventChannel.setStreamHandler(new EventChannel.StreamHandler() {

            private BroadcastReceiver chargingStateChangeReceiver;

            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {
                if (arguments != null) {
                    Log.i("flutter2Android", "arguments:" + arguments.toString() + getBatteryLevel());
                }
                chargingStateChangeReceiver = createChargingStateChangeReceiver(events);
                registerReceiver(chargingStateChangeReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            }

            @Override
            public void onCancel(Object arguments) {
                unregisterReceiver(chargingStateChangeReceiver);
                chargingStateChangeReceiver = null;
            }
        });
    }

    private int getBatteryLevel() {
        int batteryLevel = -1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        } else {
            Intent intent = new ContextWrapper(getApplicationContext()).
                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            if (intent != null)
                batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
                        intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }
        return batteryLevel;
    }


    private BroadcastReceiver createChargingStateChangeReceiver(final EventChannel.EventSink events) {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                Log.i("flutter2Android", "status:" + status);
                if (status == BatteryManager.BATTERY_STATUS_UNKNOWN) {
                    events.error("UNAVAILABLE", "Charging status unavailable", null);
                } else {
                    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL;
                    events.success(isCharging ? "charging" : "discharging");

                }
            }
        };
    }


    /**
     * 方法二:flutter调用android方法使用,也可以android调用flutter方法
     */
    MethodChannel methodChannel;

    private void initMethodChannel() {
        methodChannel = new MethodChannel(flutterEngine.getDartExecutor(), "method2Flutter");
        methodChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                Log.i("flutter2Android", call.method + ":" + result.toString());
                handleToast(MainActivity.this, call);
                String method = call.method;
                Object arguments = call.arguments;
                switch (method) {
                    case "method1":
                        result.success("成功");
                        break;
                    case "method2":
                        boolean arg;
                        if (arguments instanceof Boolean) {
                            arg = (boolean) arguments;
                        }
                        result.success("200");
                        break;
                }
            }
        });
    }

    private void send2Flutter2() {
        methodChannel.invokeMethod("PING", new ArrayList<>());
    }

    /**
     * 方法三,互传消息,收到消息回复
     */
    BasicMessageChannel<String> basicMessageChannel;

    private void initBasicMessageChannel() {
        basicMessageChannel = new BasicMessageChannel<>(flutterEngine.getDartExecutor(), "method2Flutter1", StringCodec.INSTANCE);
        basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler<String>() {
            @Override
            public void onMessage(@Nullable String message, @NonNull BasicMessageChannel.Reply<String> reply) {
                Log.i("flutter2Android", "initBasicMessageChannel:" + message + ":" + reply);
                send2Flutter3(button);
                reply.reply("200");
            }
        });
    }


    private void send2Flutter3(View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                basicMessageChannel.send("PING", new BasicMessageChannel.Reply<String>() {
                    @Override
                    public void reply(@Nullable String reply) {
                        Log.i("flutter2Android", "send2Flutter3:" + reply);
                    }
                });
            }
        }, 10000);
    }
}
