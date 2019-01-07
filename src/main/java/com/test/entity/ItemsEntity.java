package com.test.entity;

import java.sql.Date;
import java.math.BigDecimal;

public class ItemsEntity {
    private Integer iid;
    private Integer oid;
    private Integer bid;
    private String createdate;
    private Integer count;
    private String price;
    private Integer state;
    private String total_price;

    public ItemsEntity(Integer iid, Integer oid, Integer bid, String createdate, Integer count, String price, Integer state, String total_price) {
        this.iid = iid;
        this.oid = oid;
        this.bid = bid;
        this.createdate = createdate;
        this.count = count;
        this.price = price;
        this.state = state;
        this.total_price = total_price;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public ItemsEntity() {

    }
}
