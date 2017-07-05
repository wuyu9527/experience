package com.tunhuofeng.experience.Common.Dao;


import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by whx on 2017/5/11.
 */

@DatabaseTable(tableName = "bill_goods_info")
public class GoodsBillTable implements Parcelable {


    @DatabaseField(id = true)
    private int cargo_cid;
    @DatabaseField(columnName = "shop_id")
    private String shop_id;
    @DatabaseField(columnName = "item_no")
    private String item_no;
    @DatabaseField(columnName = "cargo_name")
    private String cargo_name;
    @DatabaseField(columnName = "sale_price")
    private String sale_price;
    @DatabaseField(columnName = "bale_price")
    private String bale_price;
    @DatabaseField(columnName = "price_3")
    private String price_3;
    @DatabaseField(columnName = "price_4")
    private String price_4;
    @DatabaseField(columnName = "image")
    private String image;
    @DatabaseField(columnName = "updated_at")
    private String updated_at;
    @DatabaseField(columnName = "created_at")
    private String created_at;


    private int sellCount;
    private int returnCount;
    private int num;//序号
    private String discount;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public int getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(int returnCount) {
        this.returnCount = returnCount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @ForeignCollectionField
    private ForeignCollection<CargoListBean> cargoListBeen;

    public GoodsBillTable() {

    }

    public GoodsBillTable(int cargo_cid, String shop_id, String item_no, String cargo_name, String sale_price, String bale_price, String price_3, String price_4, String image, int sellCount, int returnCount, int num) {
        this.cargo_cid = cargo_cid;
        this.shop_id = shop_id;
        this.item_no = item_no;
        this.cargo_name = cargo_name;
        this.sale_price = sale_price;
        this.bale_price = bale_price;
        this.price_3 = price_3;
        this.price_4 = price_4;
        this.image = image;
        this.sellCount = sellCount;
        this.returnCount = returnCount;
        this.num = num;
    }

    public GoodsBillTable(int cargo_cid, String shop_id, String item_no, String cargo_name, String sale_price, String bale_price, String price_3, String price_4, String image, String updated_at, String created_at) {
        this.cargo_cid = cargo_cid;
        this.shop_id = shop_id;
        this.item_no = item_no;
        this.cargo_name = cargo_name;
        this.sale_price = sale_price;
        this.bale_price = bale_price;
        this.price_3 = price_3;
        this.price_4 = price_4;
        this.image = image;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public GoodsBillTable(int cargo_cid, String shop_id, String item_no, String cargo_name, String sale_price, String bale_price, String price_3, String price_4, String image, String updated_at, String created_at, ForeignCollection<CargoListBean> cargoListBeen) {
        this.cargo_cid = cargo_cid;
        this.shop_id = shop_id;
        this.item_no = item_no;
        this.cargo_name = cargo_name;
        this.sale_price = sale_price;
        this.bale_price = bale_price;
        this.price_3 = price_3;
        this.price_4 = price_4;
        this.image = image;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.cargoListBeen = cargoListBeen;
    }


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ForeignCollection<CargoListBean> getCargoListBeen() {
        return cargoListBeen;
    }

    public void setCargoListBeen(ForeignCollection<CargoListBean> cargoListBeen) {
        this.cargoListBeen = cargoListBeen;
    }

    public int getCargo_cid() {
        return cargo_cid;
    }

    public void setCargo_cid(int cargo_cid) {
        this.cargo_cid = cargo_cid;
    }


    public String getItem_no() {
        return item_no;
    }

    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getBale_price() {
        return bale_price;
    }

    public void setBale_price(String bale_price) {
        this.bale_price = bale_price;
    }

    public String getPrice_3() {
        return price_3;
    }

    public void setPrice_3(String price_3) {
        this.price_3 = price_3;
    }

    public String getPrice_4() {
        return price_4;
    }

    public void setPrice_4(String price_4) {
        this.price_4 = price_4;
    }


    @Override
    public String toString() {
        return "GoodsBillTable{" +
                "cargo_cid=" + cargo_cid +
                ", shop_id='" + shop_id + '\'' +
                ", item_no='" + item_no + '\'' +
                ", cargo_name='" + cargo_name + '\'' +
                ", sale_price='" + sale_price + '\'' +
                ", bale_price='" + bale_price + '\'' +
                ", price_3='" + price_3 + '\'' +
                ", price_4='" + price_4 + '\'' +
                ", image='" + image + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", discount='" + discount + '\'' +
                ", num='" + num + '\'' +
                ", sellCount='" + sellCount + '\'' +
                ", returnCount='" + returnCount + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cargo_cid);
        dest.writeString(this.shop_id);
        dest.writeString(this.item_no);
        dest.writeString(this.cargo_name);
        dest.writeString(this.sale_price);
        dest.writeString(this.bale_price);
        dest.writeString(this.price_3);
        dest.writeString(this.price_4);
        dest.writeString(this.image);
        dest.writeString(this.updated_at);
        dest.writeString(this.created_at);
        dest.writeString(this.discount);
        dest.writeInt(this.num);
        dest.writeInt(this.sellCount);
        dest.writeInt(this.returnCount);

    }

    protected GoodsBillTable(Parcel in) {
        this.cargo_cid = in.readInt();
        this.shop_id = in.readString();
        this.item_no = in.readString();
        this.cargo_name = in.readString();
        this.sale_price = in.readString();
        this.bale_price = in.readString();
        this.price_3 = in.readString();
        this.price_4 = in.readString();
        this.image = in.readString();
        this.updated_at = in.readString();
        this.created_at = in.readString();
        this.discount = in.readString();
        this.num = in.readInt();
        this.sellCount = in.readInt();
        this.returnCount = in.readInt();
    }

    public static final Creator<GoodsBillTable> CREATOR = new Creator<GoodsBillTable>() {
        @Override
        public GoodsBillTable createFromParcel(Parcel source) {
            return new GoodsBillTable(source);
        }

        @Override
        public GoodsBillTable[] newArray(int size) {
            return new GoodsBillTable[size];
        }
    };
}
