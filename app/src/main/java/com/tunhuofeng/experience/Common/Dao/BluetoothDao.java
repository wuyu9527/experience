package com.tunhuofeng.experience.Common.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whx on 2017/5/9.
 */

public class BluetoothDao {

    private Context context;
    private Dao<BluetoothTable, Integer> tables;

    public BluetoothDao(Context context) {
        this.context = context;
        try {
            tables = IDatabaseHelper.getHelper(context).getBluetooth();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //增
    public synchronized void addData(BluetoothTable data) {
        try {
            tables.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删 id
    public synchronized void deleteData(int id) {
        try {
            tables.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deleteAll() {
        try {
            tables.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //查
    public List<BluetoothTable> queryAll() {

        try {
            // List<IDatabaseTable> rawResults = tables.queryForEq("count", count);
            List<BluetoothTable> rawResults = tables.queryForAll();
            return rawResults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public BluetoothTable queryFirst(){
        try {
            return tables.queryBuilder().queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改
    public synchronized int update(BluetoothTable table) {

        try {
            return tables.update(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
