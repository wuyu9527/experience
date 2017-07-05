package com.tunhuofeng.experience.Common.Dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by whx on 2017/5/11.
 */

@DatabaseTable(tableName = "cargo_list_bean")
public class CargoListBean implements Parcelable {




    public static final String ACCOUNT_ID_FIELD_NAME = "cargo_cid";
    @DatabaseField(id = true)
    private String cargo_id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ACCOUNT_ID_FIELD_NAME)
    private GoodsBillTable goodsBillTable;
    @DatabaseField
    private String shop_id;
    @DatabaseField
    private String size;
    @DatabaseField
    private String color;
    @DatabaseField
    private String is_default;
    @DatabaseField
    private String is_disabled;
    @DatabaseField
    private String sort_num;

    private String goods_id;
    private int qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public CargoListBean() {
    }

    public CargoListBean(String cargo_id, GoodsBillTable goodsBillTable, String shop_id, String size, String color, String is_default, String is_disabled, String sort_num) {
        this.cargo_id = cargo_id;
        this.goodsBillTable = goodsBillTable;
        this.shop_id = shop_id;
        this.size = size;
        this.color = color;
        this.is_default = is_default;
        this.is_disabled = is_disabled;
        this.sort_num = sort_num;
    }

    public CargoListBean(String cargo_id, GoodsBillTable goodsBillTable, String shop_id, String size, String color, int qty) {
        this.cargo_id = cargo_id;
        this.goodsBillTable = goodsBillTable;
        this.shop_id = shop_id;
        this.size = size;
        this.color = color;
        this.qty = qty;
    }

    public String getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(String cargo_id) {
        this.cargo_id = cargo_id;
    }

    public GoodsBillTable getGoodsBillTable() {
        return goodsBillTable;
    }

    public void setGoodsBillTable(GoodsBillTable goodsBillTable) {
        this.goodsBillTable = goodsBillTable;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getIs_disabled() {
        return is_disabled;
    }

    public void setIs_disabled(String is_disabled) {
        this.is_disabled = is_disabled;
    }

    public String getSort_num() {
        return sort_num;
    }

    public void setSort_num(String sort_num) {
        this.sort_num = sort_num;
    }

    @Override
    public String toString() {
        return "CargoListBean{" +
                "cargo_id='" + cargo_id + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", is_default='" + is_default + '\'' +
                ", is_disabled='" + is_disabled + '\'' +
                ", sort_num='" + sort_num + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cargo_id);
        dest.writeParcelable(this.goodsBillTable, flags);
        dest.writeString(this.shop_id);
        dest.writeString(this.size);
        dest.writeString(this.color);
        dest.writeString(this.is_default);
        dest.writeString(this.is_disabled);
        dest.writeString(this.sort_num);
        dest.writeString(this.goods_id);
        dest.writeInt(this.qty);
    }

    protected CargoListBean(Parcel in) {
        this.cargo_id = in.readString();
        this.goodsBillTable = in.readParcelable(GoodsBillTable.class.getClassLoader());
        this.shop_id = in.readString();
        this.size = in.readString();
        this.color = in.readString();
        this.is_default = in.readString();
        this.is_disabled = in.readString();
        this.sort_num = in.readString();
        this.goods_id = in.readString();
        this.qty = in.readInt();
    }

    public static final Creator<CargoListBean> CREATOR = new Creator<CargoListBean>() {
        @Override
        public CargoListBean createFromParcel(Parcel source) {
            return new CargoListBean(source);
        }

        @Override
        public CargoListBean[] newArray(int size) {
            return new CargoListBean[size];
        }
    };
}
