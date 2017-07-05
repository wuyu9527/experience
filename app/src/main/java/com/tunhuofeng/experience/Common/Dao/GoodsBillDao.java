package com.tunhuofeng.experience.Common.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by whx on 2017/5/11.
 */

public class GoodsBillDao {

    private Context context;
    private Dao<GoodsBillTable, Integer> tables;

    public GoodsBillDao(Context context) {
        this.context = context;
        try {
            tables = IDatabaseHelper.getHelper(context).getGoodsBill();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param goodsBillTables 添加货品主键信息
     */
    public synchronized boolean addGoodsBillList(final List<GoodsBillTable> goodsBillTables) {
        try {
            tables.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {

                    //                for (Article article : list) {
                    //                    tables.create(article);
                    //                }
                    for (GoodsBillTable goodsBillTable : goodsBillTables) {
                        tables.createOrUpdate(goodsBillTable);
                    }
                    return null;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * @param shop_id shop的id
     * @return
     */
    public Map<String, String> getMax(String shop_id) {
        Map<String, String> map = new HashMap<>();
        String sql1 = "select max(updated_at) from bill_goods_info where shop_id = " + shop_id;
        String sql2 = "select max(cargo_cid) from bill_goods_info where shop_id = " + shop_id;
        try {
            GenericRawResults<String[]> rawResults1 = tables.queryRaw(sql1);
            for (String[] strings : rawResults1) {
                for (String string : strings) {
                    map.put("max_updated_at", string);
                }
            }

            GenericRawResults<String[]> rawResults2 = tables.queryRaw(sql2);
            for (String[] strings : rawResults2) {
                for (String string : strings) {
                    map.put("max_cargo_cid", string);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }


    //增
    public synchronized void addData(GoodsBillTable data) {
        try {
            tables.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //增
    public synchronized void addDates(List<GoodsBillTable> dates) {
        try {
            for (GoodsBillTable data : dates) {
                tables.createOrUpdate(data);
            }
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
            List<GoodsBillTable> t = tables.queryForAll();
            if (t.size() > 0) {
                tables.delete(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //查
    public List<GoodsBillTable> queryAll() {

        try {
            // List<IDatabaseTable> rawResults = tables.queryForEq("count", count);
            List<GoodsBillTable> rawResults = tables.queryForAll();
            return rawResults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GoodsBillTable> queryCreated_at(long offset,long limit){
        try {
            List<GoodsBillTable> r = tables.queryBuilder().orderBy("created_at", false).offset(offset).limit(limit).query();//参数false表示降序，true表示升序。
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GoodsBillTable> queryForName(String name, String shop_id,long offset,long limit) {
        try {
            return tables.queryBuilder().orderBy("created_at", false).offset(offset).limit(limit).where().eq("shop_id", shop_id).and().eq("item_no", name).or().eq("cargo_name", name).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GoodsBillTable queryId(int id, String shop_id) {
        try {
            return tables.queryBuilder().where().eq("shop_id", shop_id).and().idEq(id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改
    public synchronized int update(GoodsBillTable table) {

        try {
            return tables.update(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long getCount(){
        try {
           return tables.queryBuilder().countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
