package com.test.entity;

import java.sql.Date;
import java.math.BigDecimal;

public class BooksVo {
    private String b_price;
    private Date createdate;
    private Integer state;
    private String image;
    private Integer oid;
    private String username;

    public BooksVo(String b_price, Date createdate, Integer state, String image, Integer oid, String username) {
        this.b_price = b_price;
        this.createdate = createdate;
        this.state = state;
        this.image = image;
        this.oid = oid;
        this.username = username;
    }

    public BooksVo() {
    }

    public String getB_price() {
        return b_price;
    }

    public void setB_price(String b_price) {
        this.b_price = b_price;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
