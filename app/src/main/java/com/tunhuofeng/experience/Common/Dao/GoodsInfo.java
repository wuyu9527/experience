package com.tunhuofeng.experience.Common.Dao;

import java.util.List;

/**
 * Created by whx on 2017/5/11.
 */

public class GoodsInfo {



    private int status;
    private ResultBean result;

    private List<List<String>> errors;
    private String msg;

    public String getMsg() {
        if (msg == null) {
            return "";
        } else {
            return msg;
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<List<String>> getErrors() {
        return errors;
    }

    public void setErrors(List<List<String>> errors) {
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {


        private String total;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {


            private String cargo_cid;
            private String CargoCid;
            private String Stock_;
            private String shop_id;
            private String item_no;
            private String item_year;
            private String cargo_name;
            private String sale_price;
            private String bale_price;
            private String price_3;
            private String price_4;
            private String spec_num;
            private String category_id;
            private String factory_id;
            private String image;
            private String file_id;
            private String priv_level;
            private String is_disabled;
            private String saler_id;
            private String store_id;
            private String created_at;
            private String updated_at;
            private String ImageUrl;
            private List<CargoListBean> cargo_list;

            public String getCargo_cid() {
                return cargo_cid;
            }

            public void setCargo_cid(String cargo_cid) {
                this.cargo_cid = cargo_cid;
            }

            public String getCargoCid() {
                return CargoCid;
            }

            public void setCargoCid(String CargoCid) {
                this.CargoCid = CargoCid;
            }

            public String getStock_() {
                return Stock_;
            }

            public void setStock_(String Stock_) {
                this.Stock_ = Stock_;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getItem_no() {
                return item_no;
            }

            public void setItem_no(String item_no) {
                this.item_no = item_no;
            }

            public String getItem_year() {
                return item_year;
            }

            public void setItem_year(String item_year) {
                this.item_year = item_year;
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

            public String getSpec_num() {
                return spec_num;
            }

            public void setSpec_num(String spec_num) {
                this.spec_num = spec_num;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getFactory_id() {
                return factory_id;
            }

            public void setFactory_id(String factory_id) {
                this.factory_id = factory_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getFile_id() {
                return file_id;
            }

            public void setFile_id(String file_id) {
                this.file_id = file_id;
            }

            public String getPriv_level() {
                return priv_level;
            }

            public void setPriv_level(String priv_level) {
                this.priv_level = priv_level;
            }

            public String getIs_disabled() {
                return is_disabled;
            }

            public void setIs_disabled(String is_disabled) {
                this.is_disabled = is_disabled;
            }

            public String getSaler_id() {
                return saler_id;
            }

            public void setSaler_id(String saler_id) {
                this.saler_id = saler_id;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getImageUrl() {
                return ImageUrl;
            }

            public void setImageUrl(String ImageUrl) {
                this.ImageUrl = ImageUrl;
            }

            public List<CargoListBean> getCargo_list() {
                return cargo_list;
            }

            public void setCargo_list(List<CargoListBean> cargo_list) {
                this.cargo_list = cargo_list;
            }

            public static class CargoListBean {


                private String cargo_id;
                private String cargo_cid;
                private String shop_id;
                private SpecJsonBean spec_json;
                private String is_default;
                private String is_disabled;
                private String sort_num;

                public String getCargo_id() {
                    return cargo_id;
                }

                public void setCargo_id(String cargo_id) {
                    this.cargo_id = cargo_id;
                }

                public String getCargo_cid() {
                    return cargo_cid;
                }

                public void setCargo_cid(String cargo_cid) {
                    this.cargo_cid = cargo_cid;
                }

                public String getShop_id() {
                    return shop_id;
                }

                public void setShop_id(String shop_id) {
                    this.shop_id = shop_id;
                }

                public SpecJsonBean getSpec_json() {
                    return spec_json;
                }

                public void setSpec_json(SpecJsonBean spec_json) {
                    this.spec_json = spec_json;
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

                public static class SpecJsonBean {


                    private String size;
                    private String color;

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
                }
            }
        }
    }
}
