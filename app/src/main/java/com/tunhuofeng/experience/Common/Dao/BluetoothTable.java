package com.tunhuofeng.experience.Common.Dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by whx on 2017/5/9.
 */
@DatabaseTable(tableName = "bluetooth_info")
public class BluetoothTable {

    @DatabaseField(id = true)
    private int _id;//id 0 = 80mm  1=110mm  2=针式打印
    @DatabaseField(columnName = "name")
    private String name;//蓝牙名字
    @DatabaseField(columnName = "num")
    private String num;//默认一张
    @DatabaseField(columnName = "mould")
    private String mould;//模板序号
    @DatabaseField(columnName = "mould_name")
    private String mould_name;//模板传递名称
    @DatabaseField(columnName = "address")
    private String address;// ip/蓝牙地址
    @DatabaseField(columnName = "port")
    private String port;// 端口/蓝牙端口为-1

    public BluetoothTable() {super();//需要无构造
    }

    public BluetoothTable(int _id, String name, String num, String mould, String mould_name, String address, String port) {
        this._id = _id;
        this.name = name;
        this.num = num;
        this.mould = mould;
        this.mould_name = mould_name;
        this.address = address;
        this.port = port;
    }

    public String getMould_name() {
        return mould_name;
    }

    public void setMould_name(String mould_name) {
        this.mould_name = mould_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMould() {
        return mould;
    }

    public void setMould(String mould) {
        this.mould = mould;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "BluetoothTable{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", mould='" + mould + '\'' +
                ", mould_name='" + mould_name + '\'' +
                ", address='" + address + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
