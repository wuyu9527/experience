package com.tunhuofeng.experience.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tunhuofeng.experience.R;


/**
 * Created by android on 2017/6/15.
 */

public class MyDeviceListAdapter extends ArrayAdapter<MyDeviceListAdapter.Device> {

    private int mResourceId;
    private Context context;

    public MyDeviceListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
        this.mResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Device device = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(mResourceId, null);
        TextView deviceName = (TextView) view.findViewById(R.id.deviceName);
        TextView deviceAddress = (TextView) view.findViewById(R.id.deviceAddress);

        assert device != null;
        deviceName.setText(device.getDeviceName());
        deviceAddress.setText(device.getAddress());

        return view;
    }


    public static class Device {

        private String deviceName;
        private String address;

        public Device(String deviceName, String address) {
            this.deviceName = deviceName;
            this.address = address;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
