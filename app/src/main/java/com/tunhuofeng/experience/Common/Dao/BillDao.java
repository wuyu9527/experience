package com.tunhuofeng.experience.Common.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zlt on 2016/9/8.
 */
public class BillDao {
    private Context context;
    private Dao<IDatabaseTable, Integer> billDao;

    public BillDao(Context context) {
        this.context = context;
        try {
            billDao = IDatabaseHelper.getHelper(context).getBillDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //增
    public synchronized void addData(IDatabaseTable data) {
        try {
            billDao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删 id
    public synchronized void deleteData(int id) {
        try {
            billDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删 IDatabaseTable
    public synchronized void deleteData1(IDatabaseTable data) {
        try {
            billDao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查
    public synchronized List<IDatabaseTable> query() {

        try {
           // List<IDatabaseTable> rawResults = billDao.queryForEq("count", count);
            List<IDatabaseTable> rawResults = billDao.queryForAll();
            return rawResults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查
    public List<IDatabaseTable> query(int count) {
        try {
            List<IDatabaseTable> rawResults = billDao.queryForEq("count", count);
            return rawResults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改
    public synchronized int update(int count, int id) {
        String sql = "";
        int raw = 0;
        sql = "update tb_info set count= " + count + " where _id= " + id;
        try {
            raw = billDao.updateRaw(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raw;
    }


}
