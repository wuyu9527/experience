package com.tunhuofeng.experience.Common.Dao;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zlt on 2016/9/8.
 */
@DatabaseTable(tableName = "tb_info")
public class IDatabaseTable {
    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField(columnName = "bill_type_id")
    private String bill_type_id;//单据类型
    @DatabaseField(columnName = "store_id")
    private String store_id;//门店id
    @DatabaseField(columnName = "factory_id")
    private String factory_id;//工厂id
    @DatabaseField(columnName = "count")
    private int connt;//计数次数
    @DatabaseField(columnName = "all_select")
    private String all_select;//最终查询结果

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public IDatabaseTable() {

    }

    public IDatabaseTable( String bill_type_id, String store_id, String factory_id, int connt, String all_select) {
        this.bill_type_id = bill_type_id;
        this.store_id = store_id;
        this.factory_id = factory_id;
        this.connt = connt;
        this.all_select = all_select;
    }

    public String getBill_type_id() {
        return bill_type_id;
    }

    public void setBill_type_id(String bill_type_id) {
        this.bill_type_id = bill_type_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getFactory_id() {
        return factory_id;
    }

    public void setFactory_id(String factory_id) {
        this.factory_id = factory_id;
    }

    public int getConnt() {
        return connt;
    }

    public void setConnt(int connt) {
        this.connt = connt;
    }

    public String getAll_select() {
        return all_select;
    }

    public void setAll_select(String all_select) {
        this.all_select = all_select;
    }
}
