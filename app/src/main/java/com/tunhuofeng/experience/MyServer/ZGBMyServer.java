package com.tunhuofeng.experience.MyServer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;


import com.alibaba.fastjson.JSONObject;
import com.tunhuofeng.experience.Common.Dao.CargoListBean;
import com.tunhuofeng.experience.Common.Dao.CargoListBeanDao;
import com.tunhuofeng.experience.Common.Dao.GoodsBillDao;
import com.tunhuofeng.experience.Common.Dao.GoodsBillTable;
import com.tunhuofeng.experience.Common.Dao.GoodsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

import static com.tunhuofeng.experience.Util.Constants.IN_UPDATE;
import static com.tunhuofeng.experience.Util.Constants.MY_GET_DATA_NOT_COMPLETED;
import static com.tunhuofeng.experience.Util.Constants.MY_UPDATE_COMPLETE;


/**
 * Created by whx on 2017/5/11.
 */

public class ZGBMyServer extends Service {

    private GoodsBillDao billDao;
    private CargoListBeanDao beanDao;
    private ZGBMyServerBinder binder;
    private ExecutorService sExecutorService = Executors.newCachedThreadPool();
    private int onePager = 1000;
    private static String whx = "whx";


    @Override
    public void onCreate() {
        super.onCreate();
        binder = new ZGBMyServerBinder();
        billDao = new GoodsBillDao(this);
        beanDao = new CargoListBeanDao(this);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    int sizeColor = 0;
    int goodInfo = 0;

    class ZGBGetGoodsInfo extends Thread {

        private String TK = "";
        private String max_cargo_cid = "";
        private String max_updated_at = "";
        private GoodsInfo goodsInfo;


        public ZGBGetGoodsInfo(String TK) {
            this.TK = TK;
        }

        @Override
        public void run() {
            if (goodsInfo != null) {
                List<GoodsBillTable> tables = new ArrayList<>();
                List<CargoListBean> been = new ArrayList<>();
                GoodsBillTable table;
                CargoListBean cargoListBean;
                for (GoodsInfo.ResultBean.ListBean listBean : goodsInfo.getResult().getList()) {
                    table = new GoodsBillTable(Integer.parseInt(listBean.getCargo_cid())
                            , listBean.getShop_id()
                            , listBean.getItem_no()
                            , listBean.getCargo_name()
                            , listBean.getSale_price()
                            , listBean.getBale_price()
                            , listBean.getPrice_3()
                            , listBean.getPrice_4()
                            , listBean.getImageUrl()
                            , listBean.getUpdated_at()
                            , listBean.getCreated_at()
                    );
                    tables.add(table);
                    int delete = beanDao.deleteForCid(table.getCargo_cid());//删除单个货品的规格,防止沉余规格.如有更好的方法请改进
                    //Log.i(whx,"delete:"+delete);
                    goodInfo++;
                    if (listBean.getCargo_list() != null) {
                        //Log.i(whx,"size_color:"+listBean.getCargo_list().size());
                        sizeColor += listBean.getCargo_list().size();
                        for (GoodsInfo.ResultBean.ListBean.CargoListBean bean : listBean.getCargo_list()) {
                            cargoListBean = new CargoListBean(bean.getCargo_id()
                                    , table
                                    , bean.getShop_id()
                                    , bean.getSpec_json().getSize()
                                    , bean.getSpec_json().getColor()
                                    , bean.getIs_default()
                                    , bean.getIs_disabled()
                                    , bean.getSort_num());
                            been.add(cargoListBean);
                        }
                    }
                }

                boolean inputBill = billDao.addGoodsBillList(tables);
                boolean beanBill = beanDao.addCargoListBean(been);
                if (inputBill && beanBill) {
                    Intent intent = new Intent(IN_UPDATE);
                    sendBroadcast(intent);
                    return;
                }
            }
            super.run();
        }


        public void setMax_cargo_cid(String max_cargo_cid) {
            this.max_cargo_cid = max_cargo_cid;
        }

        public void setMax_updated_at(String max_updated_at) {
            this.max_updated_at = max_updated_at;
        }

        private void getGoodsInfo() {
            JSONObject object = new JSONObject();
            object.put("max_cargo_cid", max_cargo_cid);
            object.put("max_updated_at", max_updated_at);
            object.put("limit", onePager);//每页几个
            JSONObject object1 = new JSONObject();
            object1.put("GET", object);
            RequestParams params = new RequestParams();
            params.addFormDataPart("PARAM_JSON", object1.toString());
            params.addFormDataPart("TK", TK);
            HttpRequest.post("url", params, new BaseHttpRequestCallback<GoodsInfo>() {

                @Override
                protected void onSuccess(GoodsInfo goodsInfo) {
                    super.onSuccess(goodsInfo);
                    if (goodsInfo.getStatus() == 200) {
                        ZGBGetGoodsInfo.this.goodsInfo = goodsInfo;
                        if (goodsInfo.getResult().getList().size() > 0) {
                            run();
                        } else {
                            Intent intent = new Intent(MY_UPDATE_COMPLETE);
                            intent.putExtra(MY_UPDATE_COMPLETE, "更新完成");
                            sendBroadcast(intent);
                        }
                    } else {
                        Intent intent = new Intent(MY_GET_DATA_NOT_COMPLETED);
                        if (goodsInfo.getErrors() != null) {
                            intent.putExtra(MY_GET_DATA_NOT_COMPLETED, goodsInfo.getErrors().get(0).get(2));
                        } else {
                            intent.putExtra(MY_GET_DATA_NOT_COMPLETED, goodsInfo.getMsg());
                        }
                        sendBroadcast(intent);
                    }
                }

                @Override
                public void onFailure(int errorCode, String msg) {
                    super.onFailure(errorCode, msg);
                    Intent intent = new Intent(MY_GET_DATA_NOT_COMPLETED);
                    intent.putExtra(MY_GET_DATA_NOT_COMPLETED, "网络连接失败");
                    sendBroadcast(intent);
                }
            });
        }

    }

    /**
     * @param total 总数
     * @return 总页数
     * onePager 一页几个
     */
    public int getAllPager(int total) {
        int pageCount = total / onePager + 1;
        if (total % onePager == 0) {
            pageCount = total / onePager;
        }
        return pageCount;
    }

    /**
     * 控制服务
     */
    public class ZGBMyServerBinder extends Binder {

        private ZGBGetGoodsInfo getGoodsInfo;

        /**
         * 开始任务
         */
        public void start(String TK, String id) {
            Map<String, String> map = billDao.getMax(id);
            getGoodsInfo = new ZGBGetGoodsInfo(TK);
            sExecutorService.execute(getGoodsInfo);
            if (map.get("max_updated_at") == null && map.get("max_cargo_cid") == null) {
                getGoodsInfo.getGoodsInfo();
            } else {
                inUpdate(id);
            }
        }

        /**
         * 更新任务
         */
        public void inUpdate(String id) {
            Map<String, String> map = billDao.getMax(id);
            if (!map.isEmpty()) {
                String update = map.get("max_updated_at") != null ? map.get("max_updated_at") : "";
                String sID = map.get("max_cargo_cid");
                if (sID != null && !sID.equals("")) {
                    getGoodsInfo.setMax_updated_at(update);
                    getGoodsInfo.setMax_cargo_cid(sID);
                    getGoodsInfo.getGoodsInfo();
                }
            }
        }

        /**
         * 打印
         */
        public void print() {

        }

    }


}
