package com.tunhuofeng.experience.MyView;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.tunhuofeng.experience.MyAdapter.MyDeviceListAdapter;
import com.tunhuofeng.experience.R;

import static android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_STARTED;
import static android.bluetooth.BluetoothClass.Device.Major.IMAGING;

/**
 * Created by android on 2017/6/15.
 */

public class MyDeviceListDialog extends Dialog {

    private Context context;

    public MyDeviceListDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        this.context = context;
    }

    public MyDeviceListDialog(@NonNull Context context, MyDeviceListAdapter mPairedDevicesArrayAdapter) {
        super(context, R.style.Dialog);
        this.context = context;
        this.mPairedDevicesArrayAdapter = mPairedDevicesArrayAdapter;
    }


    private ListView paired_devices;
    private ListView new_devices;
    private MyDeviceListAdapter mPairedDevicesArrayAdapter;
    private MyDeviceListAdapter mNewDevicesArrayAdapter;
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            dismiss();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        paired_devices = (ListView) findViewById(R.id.paired_devices);
        paired_devices.setAdapter(mPairedDevicesArrayAdapter);
        paired_devices.setOnItemClickListener(onItemClickListener);
        mNewDevicesArrayAdapter = new MyDeviceListAdapter(context, R.layout.item_device_name);
        new_devices = (ListView) findViewById(R.id.new_devices);
        new_devices.setAdapter(mNewDevicesArrayAdapter);
        new_devices.setOnItemClickListener(onItemClickListener);

    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setmPairedDevicesArrayAdapter(MyDeviceListAdapter mPairedDevicesArrayAdapter) {
        this.mPairedDevicesArrayAdapter = mPairedDevicesArrayAdapter;
    }


    @Override
    public void show() {
        super.show();

    }

    private boolean isFinished = false;
    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case BluetoothDevice.ACTION_FOUND:
                    isFinished = false;
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (device.getBondState() != BluetoothDevice.BOND_BONDED) {

                        if (device.getName() != null) {
                            boolean b = device.getBluetoothClass().getMajorDeviceClass() == IMAGING;

                            if (device.getBluetoothClass().getMajorDeviceClass() == IMAGING) {

                                mNewDevicesArrayAdapter.add(new MyDeviceListAdapter.Device(device.getName(), device.getAddress()));
                            }
                        }
                    }
                    break;
                case ACTION_DISCOVERY_STARTED:
                    isFinished = false;
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    isFinished = true;
                    if (mNewDevicesArrayAdapter == null) {
                        mNewDevicesArrayAdapter = new MyDeviceListAdapter(context, R.layout.item_device_name);
                    }
                    if (mNewDevicesArrayAdapter.getCount() == 0 && isFinished) {
                        mNewDevicesArrayAdapter.add(new MyDeviceListAdapter.Device("未找到蓝牙设备", ""));
                    }
                    break;
            }
        }
    };

//    @Override
//    public void dismiss() {
//        super.dismiss();
//        if (mService != null) {
//            mService.cancelDiscovery();
//        }
//        mService = null;
//    }
}
