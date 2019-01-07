package com.test.entity;

import java.sql.Date;
import java.math.BigDecimal;

public class OrdersEntity {
    private Integer oid;
    private String username;

    public OrdersEntity(Integer oid, String username) {
        this.oid = oid;
        this.username = username;
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

    public OrdersEntity() {

    }
}
