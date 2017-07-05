package com.tunhuofeng.experience.Common.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


import java.sql.SQLException;

public class IDatabaseHelper extends OrmLiteSqliteOpenHelper {

    // 数据库名称
    private static final String DATABASE_NAME = "shop_zgbBoss.db";
    // 数据库version版本
    private static final int DATABASE_VERSION = 3;
    private static volatile  IDatabaseHelper instance;

    /**
     * 构造方法
     *
     * @param context
     */
    public IDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * tableDao ，每张表对于一个
     */
    private Dao<IDatabaseTable, Integer> bill_Dao;
    private Dao<BluetoothTable, Integer> bluetooth;
    private Dao<GoodsBillTable, Integer> goodsBill;
    private Dao<CargoListBean, Integer> cargoListBeen;

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // 创建表
        try {
            TableUtils.createTable(connectionSource, IDatabaseTable.class);
            TableUtils.createTable(connectionSource, BluetoothTable.class);
            TableUtils.createTable(connectionSource, GoodsBillTable.class);
            TableUtils.createTable(connectionSource, CargoListBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //数据表版本更新则调用此方法
        try {
            TableUtils.dropTable(connectionSource, IDatabaseTable.class, true);
            TableUtils.dropTable(connectionSource, BluetoothTable.class, true);
            TableUtils.dropTable(connectionSource, GoodsBillTable.class, true);
            TableUtils.dropTable(connectionSource, CargoListBean.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取单例
     *
     * @param context
     * @return
     */
    public static synchronized IDatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (IDatabaseHelper.class) {
                if (instance == null) {
                    instance = new IDatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获得 bill_Dao
     *
     * @return
     * @throws SQLException
     */
    public Dao<IDatabaseTable, Integer> getBillDao() throws SQLException {
        if (bill_Dao == null) {
            bill_Dao = getDao(IDatabaseTable.class);
        }
        return bill_Dao;
    }

    /**
     * 获得 bluetooth
     *
     * @return
     * @throws SQLException
     */
    public Dao<BluetoothTable, Integer> getBluetooth() throws SQLException {
        if (bluetooth == null) {
            bluetooth = getDao(BluetoothTable.class);
        }
        return bluetooth;
    }

    /**
     * 获得 GoodsBillInfo
     *
     * @return
     * @throws SQLException
     */
    public Dao<GoodsBillTable, Integer> getGoodsBill() throws SQLException {
        if (goodsBill == null) {
            goodsBill = getDao(GoodsBillTable.class);
        }
        return goodsBill;
    }

    /**
     * 获得 CargoListBean
     *
     * @return
     * @throws SQLException
     */
    public Dao<CargoListBean, Integer> getCargoListBean() throws SQLException {
        if (cargoListBeen == null) {
            cargoListBeen = getDao(CargoListBean.class);
        }
        return cargoListBeen;
    }

    @Override
    public void close() {
        super.close();
        bill_Dao=null;
    }
}
