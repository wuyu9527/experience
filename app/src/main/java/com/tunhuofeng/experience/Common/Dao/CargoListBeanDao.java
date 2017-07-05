package com.tunhuofeng.experience.Common.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by whx on 2017/5/11.
 */

public class CargoListBeanDao {
    private Context context;
    private Dao<CargoListBean, Integer> been;

    public CargoListBeanDao(Context context) {
        this.context = context;
        try {
            been = IDatabaseHelper.getHelper(context).getCargoListBean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param cargoListBeen 添加货品规格信息
     */
    public synchronized boolean addCargoListBean(final List<CargoListBean> cargoListBeen){
        try {
            been.callBatchTasks(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    for (CargoListBean cargoListBean : cargoListBeen) {
                        been.createOrUpdate(cargoListBean);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }



    //增
    public synchronized void addData(CargoListBean data) {
        try {
            been.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //增
    public synchronized void addDates(List<CargoListBean> dates) {
        try {
            for (CargoListBean data : dates) {
                been.createOrUpdate(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删 id
    public synchronized void deleteData(int id) {
        try {
            been.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized int deleteForCid(int id){
        try {
            DeleteBuilder deleteBuilder=been.deleteBuilder();
            deleteBuilder.where().eq("cargo_cid", id);
            return deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public synchronized  void deleteAll(){
        try {
            been.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //查
    public List<CargoListBean> queryAll() {

        try {
            // List<IDatabaseTable> rawResults = tables.queryForEq("count", count);
            List<CargoListBean> rawResults = been.queryForAll();
            return rawResults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id 查询cargo_cid
     * @return
     */
    public List<CargoListBean> queryForC(int id,String shop_id){
        try {
            return been.queryBuilder().where().eq("cargo_cid", id).and().eq("shop_id",shop_id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CargoListBean query(int id){

        try {
            return been.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //修改
    public synchronized int update(CargoListBean bean) {

        try {
            return been.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
