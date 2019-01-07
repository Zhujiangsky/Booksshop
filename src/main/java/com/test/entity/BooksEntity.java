package com.test.entity;

import java.sql.Date;
import java.math.BigDecimal;

public class BooksEntity {
    private Integer bid;
    private String bookname;
    private String b_price;
    private String image;
    private Integer stock;

    public BooksEntity(Integer bid, String bookname, String b_price, String image, Integer stock) {
        this.bid = bid;
        this.bookname = bookname;
        this.b_price = b_price;
        this.image = image;
        this.stock = stock;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getB_price() {
        return b_price;
    }

    public void setB_price(String b_price) {
        this.b_price = b_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BooksEntity() {

    }
}
